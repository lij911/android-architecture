package com.lijing.dev.mvps.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lijing.dev.mvps.presenter.BaseAbstractPresenter;

import javax.inject.Inject;

/**
 * 我们的目标是，尽量不用 fragment
 * 2.0 该用还是要用啊
 *
 * @author lijing
 */
public abstract class BaseAbstractFragment<P extends BaseAbstractPresenter> extends Fragment implements IBaseFragment {
    protected final String TAG = this.getClass().getSimpleName();

    @Inject
    protected P mPresenter;

    protected Activity mContext;

    protected View mRootView;

    protected KProgressHUD mProgressHUD;

//    protected Unbinder mUnbinder;

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        mContext = context;
        initInject();
        attachPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentLayoutID() != 0) {
             mRootView = inflater.inflate(getContentLayoutID(), null);

            return mRootView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mUnbinder = ButterKnife.bind(this, mRootView);
        initVariables();
        initViews();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvents();
        initData();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenter != null) {
            mPresenter.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        showHud(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (mUnbinder != null) {
//            mUnbinder.unbind();
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        detachPresenter();
    }

    /* presenter */

    private void attachPresenter() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    private void detachPresenter() {
        if (mPresenter != null) {
            mProgressHUD = null;
            mPresenter.unsubscribe();
            mPresenter.detachView();
        }
    }


    @Override
    public void showHud(boolean b) {
        if (mProgressHUD == null) {
            mProgressHUD = KProgressHUD
                    .create(mContext)
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
    public void showMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}
