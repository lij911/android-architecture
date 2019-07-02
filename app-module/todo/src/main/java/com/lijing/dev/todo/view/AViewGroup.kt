package com.lijing.dev.todo.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.lijing.dev.utils.DensityUtil

class AViewGroup : ViewGroup {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var index = 0
        while (index < childCount) {
            // 测量所有子 View
            val child = getChildAt(index)
            val layoutParams = child.layoutParams
            measureChild(child, getChildMeasureSpec(widthMeasureSpec, 0, layoutParams.width),
                    getChildMeasureSpec(heightMeasureSpec, 0, layoutParams.height))
            index++
        }
        // 设置自己的大小
        setMeasuredDimension(calculateWidthMeasureSpec(widthMeasureSpec), calculateHeightMeasureSpec(heightMeasureSpec))
    }

    /**
     * 高度等于所有 children 的和
     */
    private fun calculateHeightMeasureSpec(measureSpec: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)

        return when (mode) {
            MeasureSpec.UNSPECIFIED, MeasureSpec.EXACTLY -> size
            MeasureSpec.AT_MOST -> {
                var index = 0
                var result = 0
                while (index < childCount) {
                    // 测量所有子 View
                    val child = getChildAt(index)
                    result += child.measuredHeight
                    index++
                }
                result
            }
            else -> 0
        }
    }

    private fun calculateWidthMeasureSpec(measureSpec: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        return when (mode) {
            MeasureSpec.UNSPECIFIED, MeasureSpec.EXACTLY -> size
            MeasureSpec.AT_MOST -> {
                val child = getChildAt(childCount - 1)
                child.measuredWidth + DensityUtil.dip2px(context, 30.0F) * (childCount - 1)
            }
            else -> 0
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (!changed) {
            return
        }
        var index = 0
        var top = 0
        while (index < childCount) {
            val child = getChildAt(index)
            // 每个子 View 比上一个子 View 向右偏移 30dp
            val offset = index * DensityUtil.dip2px(context, 30.0F)
            child.layout(offset, top, offset + child.measuredWidth, top + child.measuredHeight)
            // 竖直方向上，逐渐向下排布
            top += child.measuredHeight
            index++
        }
    }
}
