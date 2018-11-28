package com.lijing.dev.network.Interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * @author lijing
 */
public class BasicParamsInterceptor implements Interceptor {

    private Map<String, String> headerParamsMap = new HashMap<>();
    private List<String> headerLinesList = new ArrayList<>();

    private Map<String, String> queryParamsMap = new HashMap<>();
    private Map<String, String> bodyParamsMap = new HashMap<>();

    private List<String> urlWhitelist = new ArrayList<>();

    public static final String POST = "POST";
    public static final String X_WWW_FORM_URLENCODED = "x-www-form-urlencoded";

    private BasicParamsInterceptor() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Headers.Builder headerBuilder = request.headers().newBuilder();
        Request.Builder requestBuilder = request.newBuilder();

        if (filterWhitelist(request)) {
            return chain.proceed(request);
        }
        if (!headerParamsMap.isEmpty()) {
            injectHeaderParams(headerBuilder);
        }
        // 直接拼接 params 到 url 中
        if (queryParamsMap.size() > 0) {
            injectParamsIntoUrl(request, requestBuilder, queryParamsMap);
        }

        if (injectPostBody(request, requestBuilder)) {
            // 不是 post 请求，把 bodyParamsMap 拼接到 url 中
            injectParamsIntoUrl(request, requestBuilder, bodyParamsMap);
        }

        requestBuilder.headers(headerBuilder.build());
        request = requestBuilder.build();
        return chain.proceed(request);
    }

    /**
     * 白名单内的 url 不做拦截
     *
     * @param request
     * @return
     * @throws IOException
     */
    private boolean filterWhitelist(Request request) throws IOException {
        // filter white url
        for (String url : urlWhitelist) {
            String s = request.url().encodedPath();
            if (s.endsWith(url)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 添加 Header 的参数
     *
     * @param headerBuilder
     */
    private void injectHeaderParams(Headers.Builder headerBuilder) {
        if (headerParamsMap.size() > 0) {
            for (Map.Entry entry : headerParamsMap.entrySet()) {
                headerBuilder.add((String) entry.getKey(), (String) entry.getValue());
            }
        }

        if (headerLinesList.size() > 0) {
            for (String line : headerLinesList) {
                headerBuilder.add(line);
            }
        }
    }

    /**
     * post 请求时，插入参数到 request body
     *
     * @param request
     * @param requestBuilder
     * @return
     */
    private boolean injectPostBody(Request request, Request.Builder requestBuilder) {
        if (POST.equals(request.method()) && X_WWW_FORM_URLENCODED.equals(request.body().contentType().subtype())) {
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            if (bodyParamsMap.size() > 0) {
                for (Map.Entry entry : bodyParamsMap.entrySet()) {
                    formBodyBuilder.add((String) entry.getKey(), (String) entry.getValue());
                }
            }
            RequestBody formBody = formBodyBuilder.build();
            String postBodyString = bodyToString(request.body());
            postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
            requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString));
            return true;
        }
        return false;
    }

    /**
     * 拼接参数到 header url 中
     * @param request
     * @param requestBuilder
     * @param paramsMap
     */
    private void injectParamsIntoUrl(Request request, Request.Builder requestBuilder, Map<String, String> paramsMap) {
        HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
        if (paramsMap.size() > 0) {
            for (Map.Entry entry : paramsMap.entrySet()) {
                httpUrlBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
        }

        requestBuilder.url(httpUrlBuilder.build());
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null) {
                copy.writeTo(buffer);
            } else {
                return "";
            }
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    public static class Builder {

        BasicParamsInterceptor interceptor;

        public Builder() {
            interceptor = new BasicParamsInterceptor();
        }

        public Builder addParam(String key, String value) {
            interceptor.bodyParamsMap.put(key, value);
            return this;
        }

        public Builder addParamsMap(Map<String, String> paramsMap) {
            interceptor.bodyParamsMap.putAll(paramsMap);
            return this;
        }

        public Builder addHeaderParam(String key, String value) {
            interceptor.headerParamsMap.put(key, value);
            return this;
        }

        public Builder addHeaderParamsMap(Map<String, String> headerParamsMap) {
            interceptor.headerParamsMap.putAll(headerParamsMap);
            return this;
        }

        public Builder addHeaderLine(String headerLine) {
            int index = headerLine.indexOf(":");
            if (index == -1) {
                throw new IllegalArgumentException("Unexpected header: " + headerLine);
            }
            interceptor.headerLinesList.add(headerLine);
            return this;
        }

        public Builder addHeaderLinesList(List<String> headerLinesList) {
            for (String headerLine : headerLinesList) {
                int index = headerLine.indexOf(":");
                if (index == -1) {
                    throw new IllegalArgumentException("Unexpected header: " + headerLine);
                }
                interceptor.headerLinesList.add(headerLine);
            }
            return this;
        }

        public Builder addWhiteListUrl(String whiteUrl) {
            interceptor.urlWhitelist.add(whiteUrl);
            return this;
        }

        public Builder addQueryParam(String key, String value) {
            interceptor.queryParamsMap.put(key, value);
            return this;
        }

        public Builder addQueryParamsMap(Map<String, String> queryParamsMap) {
            interceptor.queryParamsMap.putAll(queryParamsMap);
            return this;
        }

        public BasicParamsInterceptor build() {
            return interceptor;
        }

    }
}