package com.blacksmithdreamapps.presentgo.seekbar


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.02.2018.
 */

class CircleBubbleView : View {
    private var mIndicatorTextColor: Int = 0
    private var mIndicatorColor: Int = 0
    private var mIndicatorTextSize: Int = 0
    private var mContext: Context? = null
    private var mPath: Path? = null
    private var mPaint: Paint? = null
    private var mIndicatorWidth: Float = 0.toFloat()
    private var mIndicatorHeight: Float = 0.toFloat()
    private var mTextHeight: Float = 0.toFloat()
    private var mProgress: String? = null

    @JvmOverloads internal constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        init("100")
    }

    constructor(p: BuilderParams, maxLengthText: String) : super(p.mContext, null, 0) {
        this.mContext = p.mContext
        this.mIndicatorTextSize = p.mIndicatorTextSize
        this.mIndicatorTextColor = p.mIndicatorTextColor
        this.mIndicatorColor = p.mIndicatorColor
        init(maxLengthText)
    }

    private fun init(maxLengthText: String) {
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
        mPaint!!.strokeWidth = 1f
        mPaint!!.textAlign = Paint.Align.CENTER
        mPaint!!.textSize = mIndicatorTextSize.toFloat()
        val mRect = Rect()
        mPaint!!.getTextBounds(maxLengthText, 0, maxLengthText.length, mRect)
        mIndicatorWidth = (mRect.width() + IndicatorUtils.dp2px(mContext!!, 4f)).toFloat()
        val minWidth = IndicatorUtils.dp2px(mContext!!, 36f)
        if (mIndicatorWidth < minWidth) {
            mIndicatorWidth = minWidth.toFloat()
        }
        mTextHeight = mRect.height().toFloat()
        mIndicatorHeight = mIndicatorWidth * 1.2f
        initPath()
    }

    private fun initPath() {
        mPath = Path()
        val rectF = RectF(0f, 0f, mIndicatorWidth, mIndicatorWidth)
        mPath!!.arcTo(rectF, 135f, 270f)
        mPath!!.lineTo(mIndicatorWidth / 2, mIndicatorHeight)
        mPath!!.close()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(mIndicatorWidth.toInt(), mIndicatorHeight.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        mPaint!!.color = mIndicatorColor
        canvas.drawPath(mPath!!, mPaint!!)
        mPaint!!.color = mIndicatorTextColor
        canvas.drawText(mProgress!!, mIndicatorWidth / 2f, mIndicatorHeight / 2 + mTextHeight / 4, mPaint!!)
    }

    fun setProgress(progress: String) {
        this.mProgress = progress
        invalidate()
    }

}
