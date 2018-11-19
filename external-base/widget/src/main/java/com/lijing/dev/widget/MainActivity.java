package com.lijing.dev.widget;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lijing.dev.widget.recyclerview.adapter.BaseCommonAdapter;
import com.lijing.dev.widget.recyclerview.adapter.BaseViewHolder;

import java.util.List;

/**
 * @author lijing
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter adapter = new MainAdapter(R.layout.widget_main_item, null);
        recyclerView.setAdapter(adapter);
        adapter.addItem(new Object());
        adapter.addItem(new Object());
        adapter.addItem(new Object());
        adapter.notifyDataSetChanged();
    }

    private class MainAdapter extends BaseCommonAdapter<Object, BaseViewHolder> {

        public MainAdapter(int layoutId, @Nullable List<Object> data) {
            super(layoutId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Object item, int postion) {
            helper.setText(R.id.tv_title, "123");
        }
    }
}
