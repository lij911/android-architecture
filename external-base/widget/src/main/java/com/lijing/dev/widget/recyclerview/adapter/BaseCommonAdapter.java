package com.lijing.dev.widget.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * 常用 RecyclerView 的 Adapter
 *
 * @author lijing
 */
public class BaseCommonAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {



    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
