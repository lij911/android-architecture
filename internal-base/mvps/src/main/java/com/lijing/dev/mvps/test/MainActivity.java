package com.lijing.dev.mvps.test;

import android.view.View;
import android.widget.Button;

import com.lijing.dev.mvps.R;
import com.lijing.dev.mvps.activity.BaseAbstractActivity;
import com.lijing.dev.mvps.di.DaggerBaseMvpsComponent;

public class MainActivity extends BaseAbstractActivity<MainPresenter> {


    @Override
    public int getContentLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initVariables() {

    }

    @Override
    public void initViews() {
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.show("123123");
            }
        });
    }

    @Override
    public void initEvents() {
    }

    @Override
    public void initInject() {
        DaggerBaseMvpsComponent.getInstance().inject(this);
    }

    @Override
    public void initData() {
    }
}
