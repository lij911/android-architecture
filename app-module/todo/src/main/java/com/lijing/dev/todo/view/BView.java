package com.lijing.dev.todo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.lijing.dev.utils.DensityUtil;

public class BView extends View {

    public BView(Context context) {
        super(context);
    }

    public BView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(doLocalMeasure(DensityUtil.INSTANCE.dip2px(getContext(), 60), widthMeasureSpec),
                doLocalMeasure(DensityUtil.INSTANCE.dip2px(getContext(), 40), heightMeasureSpec));
    }

    int doLocalMeasure(int defaultSize, int measureSpec) {
        int result = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.EXACTLY:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(result, size);
                break;
            default:
                break;

        }
        return result;
    }


}
