package com.lijing.dev.network;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author lijing
 */
public class GeneralParams {

    /**
     * 白名单内的 URL 不会添加以下所有参数
     */
    public static final List<String> FILTER_URLS = new LinkedList<String>() {
        {
//            add("");
        }
    };

    /**
     * 请求头中添加的参数
     */
    public static final Map<String, String> HEADER_PARAMS = new HashMap<String, String>() {
        {
//            put("","");
        }
    };

    /**
     * request body 中添加的参数
     * GET 请求，拼接到 URL 中
     * POST 请求，添加到 request body 中
     */
    public static final Map<String, String> BODY_PARAMS = new HashMap<String, String>() {
        {
        }
    };

    /**
     * 拼接到 request URL 中
     */
    public static final Map<String, String> QUERY_PARAMS = new HashMap<String, String>() {
        {
        }
    };


}
