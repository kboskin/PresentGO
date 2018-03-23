package com.blacksmithdreamapps.presentgo.seekbar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View


/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.02.2018.
 */

class ArrowView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private val mWidth: Int
    private val mHeight: Int
    private val mPath: Path
    private val mPaint: Paint

    init {
        mWidth = IndicatorUtils.dp2px(context, 12f)
        mHeight = IndicatorUtils.dp2px(context, 7f)
        mPath = Path()
        mPath.moveTo(0f, 0f)
        mPath.lineTo(mWidth.toFloat(), 0f)
        mPath.lineTo(mWidth / 2f, mHeight.toFloat())
        mPath.close()
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 1f
    }

    fun setColor(color: Int) {
        mPaint.color = color
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(mWidth, mHeight)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(mPath, mPaint)
    }
}
