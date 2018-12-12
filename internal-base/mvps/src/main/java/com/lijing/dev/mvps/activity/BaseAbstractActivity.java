package com.lijing.dev.mvps.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lijing.dev.mvps.presenter.BaseAbstractPresenter;

import javax.inject.Inject;

/**
 * activity 的抽象基类
 *
 * @author lijing
 */
public abstract class BaseAbstractActivity<P extends BaseAbstractPresenter> extends AppCompatActivity implements IBaseActivity {

    @Inject
    protected P mPresenter;

    protected KProgressHUD mProgressHUD;


    /**
     * Activity 初始化时调用，用于设置全局数据
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutID());
        initInject();
        attachPresenter();
        initVariables();
        initViews();
        initEvents();
        initData();
    }

    /**
     * Activity 可见时调用
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Activity 获取焦点时调用
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Activity 失去焦点时调用，持久化数据的最后一次可靠的机会
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Activity 不可见时调用
     */
    @Override
    protected void onStop() {
        showHud(false);
        super.onStop();
    }

    /**
     * 和 onCreate 对应，activity 被摧毁时调用，如 finish
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        detachPresenter();
    }

    /**
     * onStop 之前调用
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenter != null) {
            mPresenter.onSaveInstanceState(outState);
        }
    }

    @SuppressWarnings("unchecked")
    private void attachPresenter() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
            mPresenter.mLiveHud.observe(this, (Observer<Boolean>) this::showHud);
            mPresenter.mLiveMessage.observe(this, (Observer<String>) this::showMessage);
        }
    }

    private void detachPresenter() {
        if (mPresenter != null) {
            mPresenter.unsubscribe();
            mPresenter.detachView();
        }
    }

    @Override
    public final void showHud(boolean b) {
        if (mProgressHUD == null) {
            mProgressHUD = KProgressHUD
                    .create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f);
        }

        if (mProgressHUD.isShowing() && !b) {
            mProgressHUD.dismiss();
        } else if (!mProgressHUD.isShowing() && b) {
            mProgressHUD.show();
        }
    }

    @Override
    public final void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
