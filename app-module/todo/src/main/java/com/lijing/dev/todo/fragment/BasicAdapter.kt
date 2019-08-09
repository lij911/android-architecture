package com.lijing.dev.todo.fragment

import com.lijing.dev.todo.R
import com.lijing.dev.widget.recyclerview.BaseViewHolder
import com.lijing.dev.widget.recyclerview.adapter.BaseCommonAdapter


class BasicAdapter(layoutId: Int = R.layout.item_basic_content) : BaseCommonAdapter<String, BaseViewHolder>(layoutId) {

    override fun convert(helper: BaseViewHolder, item: String?, position: Int) {
        helper.setText(R.id.tv_title, item)
    }

}