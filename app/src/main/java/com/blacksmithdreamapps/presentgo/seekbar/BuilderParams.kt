package com.blacksmithdreamapps.presentgo.seekbar

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View


/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.02.2018.
 */


class BuilderParams(var mContext: Context) {
    //seekBar
    var mSeekBarType = 0
    var mMax = 100f
    var mMin = 0f
    var mProgress = 0f
    var mClearPadding = false
    var mIsFloatProgress = false
    var mForbidUserSeek = false
    var mTouchToSeek = true
    //indicator
    var mIndicatorType = 0
    var mShowIndicator = true
    var mIndicatorStay = false
    var mIndicatorColor = Color.parseColor("#FF4081")
    var mIndicatorTextColor = Color.parseColor("#FFFFFF")
    var mIndicatorTextSize: Int = 0
    var mIndicatorCustomView: View? = null
    var mIndicatorCustomTopContentView: View? = null
    //track
    var mBackgroundTrackSize: Int = 0
    var mProgressTrackSize: Int = 0
    var mBackgroundTrackColor = Color.parseColor("#D7D7D7")
    var mProgressTrackColor = Color.parseColor("#FF4081")
    var mTrackRoundedCorners = true
    //tick
    var mTickNum = 5
    var mTickType = 1
    var mTickSize: Int = 0
    var mTickColor = Color.parseColor("#FF4081")
    var mTickHideBothEnds = false
    var mTickOnThumbLeftHide = false
    var mTickDrawable: Drawable? = null
    //text
    var mTextSize: Int = 0
    var mTextColor = Color.parseColor("#FF4081")
    var mLeftEndText: String? = null
    var mRightEndText: String? = null
    var mTextArray: Array<CharSequence>? = null
    var mTextTypeface = Typeface.DEFAULT
    //thumb
    var mThumbColor = Color.parseColor("#FF4081")
    var mThumbSize: Int = 0
    var mThumbDrawable: Drawable? = null
    var mThumbProgressStay = false

    init {
        this.mIndicatorTextSize = IndicatorUtils.sp2px(mContext, 13f)
        this.mBackgroundTrackSize = IndicatorUtils.dp2px(mContext, 2f)
        this.mProgressTrackSize = IndicatorUtils.dp2px(mContext, 2f)
        this.mTickSize = IndicatorUtils.dp2px(mContext, 10f)
        this.mTextSize = IndicatorUtils.sp2px(mContext, 13f)
        this.mThumbSize = IndicatorUtils.dp2px(mContext, 14f)
    }

    fun copy(p: BuilderParams): BuilderParams {
        this.mContext = p.mContext
        //seekBar
        this.mSeekBarType = p.mSeekBarType
        this.mMax = p.mMax
        this.mMin = p.mMin
        this.mProgress = p.mProgress
        this.mClearPadding = p.mClearPadding
        this.mIsFloatProgress = p.mIsFloatProgress
        this.mForbidUserSeek = p.mForbidUserSeek
        this.mTouchToSeek = p.mTouchToSeek
        //indicator
        this.mIndicatorType = p.mIndicatorType
        this.mShowIndicator = p.mShowIndicator
        this.mIndicatorStay = p.mIndicatorStay
        this.mIndicatorColor = p.mIndicatorColor
        this.mIndicatorTextColor = p.mIndicatorTextColor
        this.mIndicatorTextSize = p.mIndicatorTextSize
        this.mIndicatorCustomView = p.mIndicatorCustomView
        this.mIndicatorCustomTopContentView = p.mIndicatorCustomTopContentView
        //track
        this.mBackgroundTrackSize = p.mBackgroundTrackSize
        this.mProgressTrackSize = p.mProgressTrackSize
        this.mBackgroundTrackColor = p.mBackgroundTrackColor
        this.mProgressTrackColor = p.mProgressTrackColor
        this.mTrackRoundedCorners = p.mTrackRoundedCorners
        //tick
        this.mTickNum = p.mTickNum
        this.mTickType = p.mTickType
        this.mTickSize = p.mTickSize
        this.mTickColor = p.mTickColor
        this.mTickHideBothEnds = p.mTickHideBothEnds
        this.mTickOnThumbLeftHide = p.mTickOnThumbLeftHide
        this.mTickDrawable = p.mTickDrawable
        //text
        this.mTextSize = p.mTextSize
        this.mTextColor = p.mTextColor
        this.mLeftEndText = p.mLeftEndText
        this.mRightEndText = p.mRightEndText
        this.mTextArray = p.mTextArray
        this.mTextTypeface = p.mTextTypeface
        //thumb
        this.mThumbColor = p.mThumbColor
        this.mThumbSize = p.mThumbSize
        this.mThumbDrawable = p.mThumbDrawable
        this.mThumbProgressStay = p.mThumbProgressStay
        return this
    }
}
