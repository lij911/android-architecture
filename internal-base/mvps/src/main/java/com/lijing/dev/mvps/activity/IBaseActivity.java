package com.lijing.dev.mvps.activity;

import com.lijing.dev.mvps.IBaseView;

/**
 * activity 所具备的方法
 *
 * @author lijing
 */
public interface IBaseActivity extends IBaseView {

    /**
     * 获取内容视图
     *
     * @return
     */
    int getContentLayoutID();

    /**
     * 初始化变量
     */
    void initVariables();

    /**
     * 初始化控件
     */
    void initViews();

    /**
     * 初始化事件
     */
    void initEvents();

    /**
     * 初始化dagger注入
     */
    void initInject();

    /**
     * 初始化数据
     */
    void initData();

}
