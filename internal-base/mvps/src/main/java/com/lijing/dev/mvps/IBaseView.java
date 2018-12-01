package com.lijing.dev.mvps;

/**
 * view 层的通用功能
 *
 * @author lijing
 */
public interface IBaseView {

    /**
     * 加载动画
     *
     * @param b
     */
    void showHud(boolean b);

    /**
     * toast 提示
     *
     * @param msg
     */
    void showMessage(String msg);
}
