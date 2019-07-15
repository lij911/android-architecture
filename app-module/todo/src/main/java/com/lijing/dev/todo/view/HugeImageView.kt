package com.lijing.dev.todo.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DebugUtils
import android.view.View
import com.lijing.dev.core.annotation.AppScope
import com.lijing.dev.utils.BitmapUtils
import com.lijing.dev.utils.CoroutinesUtils
import com.lijing.dev.utils.DensityUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.net.URL

/**
 * 超级大图显示类
 */
class HugeImageView : View {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    /**
     * 图片实际高度和宽度
     */
    private var imageActualHeight = 0
    private var imageActualWidth = 0

    /**
     * Bitmap 相关
     */
    private var bitmapRegionDecoder: BitmapRegionDecoder? = null
    private var option: BitmapFactory.Options = BitmapFactory.Options().let {
        it.inPreferredConfig = Bitmap.Config.RGB_565
        it
    }

    private val rect = Rect()

    fun loadDrawable(drawableRes: String) {
        val inputStream = context.assets.open(drawableRes)
        BitmapFactory.decodeStream(inputStream, null, option)
        imageActualHeight = option.outHeight
        imageActualWidth = option.outWidth
        bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false)
        inputStream.close()
        setWillNotDraw(false)
        requestLayout()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        rect.set(0, 0, measuredHeight, measuredWidth)
        bitmapRegionDecoder?.decodeRegion(rect, option)?.apply {
            canvas?.drawBitmap(this, 0F, 0F, null)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(customMeasure(DensityUtil.dip2px(context, 120F), widthMeasureSpec),
                customMeasure(DensityUtil.dip2px(context, 120F), heightMeasureSpec))
    }

    private fun customMeasure(defaultSize: Int, measureSpec: Int): Int {
        var result = defaultSize
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        when (mode) {
            MeasureSpec.UNSPECIFIED, MeasureSpec.EXACTLY -> result = size
            MeasureSpec.AT_MOST -> result = Math.min(result, size)
            else -> {
            }
        }
        return result
    }
}
