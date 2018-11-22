package com.lijing.dev.widget;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.lijing.dev.widget.recyclerview.adapter.BaseCommonAdapter;
import com.lijing.dev.widget.recyclerview.adapter.BaseViewHolder;

import java.util.ArrayList;
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
        adapter.addItems("item 1", "item 2", "item 3");
        adapter.addItems(0, "item A", "item B", "item C");
        adapter.addOnItemClickListener((viewHolder, position) -> {
            Toast.makeText(this, adapter.getItem(position), Toast.LENGTH_SHORT).show();
        });
    }

    private class MainAdapter extends BaseCommonAdapter<String, BaseViewHolder> {

        public MainAdapter(int layoutId, @Nullable List<String> data) {
            super(layoutId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item, int position) {
            helper.setText(R.id.tv_title, item);
        }
    }
}
