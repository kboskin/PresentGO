package com.kosboss.gogift.seekbar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * created by ZhuangGuangquan on 2017/9/9
 * Version : 2.0
 * Date: 2017/12/10
 * New Feature: indicator stay always.
 */


public class BuilderParams {

    public Context mContext;
    //seekBar
    public int mSeekBarType = 0;
    public float mMax = 100;
    public float mMin = 0;
    public float mProgress = 0;
    public boolean mClearPadding = false;
    public boolean mIsFloatProgress = false;
    public boolean mForbidUserSeek = false;
    public boolean mTouchToSeek = true;
    //indicator
    public int mIndicatorType = 0;
    public boolean mShowIndicator = true;
    public boolean mIndicatorStay = false;
    public int mIndicatorColor = Color.parseColor("#FF4081");
    public int mIndicatorTextColor = Color.parseColor("#FFFFFF");
    public int mIndicatorTextSize;
    public View mIndicatorCustomView = null;
    public View mIndicatorCustomTopContentView = null;
    //track
    public int mBackgroundTrackSize;
    public int mProgressTrackSize;
    public int mBackgroundTrackColor = Color.parseColor("#D7D7D7");
    public int mProgressTrackColor = Color.parseColor("#FF4081");
    public boolean mTrackRoundedCorners = true;
    //tick
    public int mTickNum = 5;
    public int mTickType = 1;
    public int mTickSize;
    public int mTickColor = Color.parseColor("#FF4081");
    public boolean mTickHideBothEnds = false;
    public boolean mTickOnThumbLeftHide = false;
    public Drawable mTickDrawable = null;
    //text
    public int mTextSize;
    public int mTextColor = Color.parseColor("#FF4081");
    public String mLeftEndText = null;
    public String mRightEndText = null;
    public CharSequence[] mTextArray = null;
    public Typeface mTextTypeface = Typeface.DEFAULT;
    //thumb
    public int mThumbColor = Color.parseColor("#FF4081");
    public int mThumbSize;
    public Drawable mThumbDrawable = null;
    public boolean mThumbProgressStay = false;

    public BuilderParams(Context context) {
        this.mContext = context;
        this.mIndicatorTextSize = IndicatorUtils.INSTANCE.sp2px(mContext, 13);
        this.mBackgroundTrackSize = IndicatorUtils.INSTANCE.dp2px(mContext, 2);
        this.mProgressTrackSize = IndicatorUtils.INSTANCE.dp2px(mContext, 2);
        this.mTickSize = IndicatorUtils.INSTANCE.dp2px(mContext, 10);
        this.mTextSize = IndicatorUtils.INSTANCE.sp2px(mContext, 13);
        this.mThumbSize = IndicatorUtils.INSTANCE.dp2px(mContext, 14);
    }

    public BuilderParams copy(BuilderParams p) {
        this.mContext = p.mContext;
        //seekBar
        this.mSeekBarType = p.mSeekBarType;
        this.mMax = p.mMax;
        this.mMin = p.mMin;
        this.mProgress = p.mProgress;
        this.mClearPadding = p.mClearPadding;
        this.mIsFloatProgress = p.mIsFloatProgress;
        this.mForbidUserSeek = p.mForbidUserSeek;
        this.mTouchToSeek = p.mTouchToSeek;
        //indicator
        this.mIndicatorType = p.mIndicatorType;
        this.mShowIndicator = p.mShowIndicator;
        this.mIndicatorStay = p.mIndicatorStay;
        this.mIndicatorColor = p.mIndicatorColor;
        this.mIndicatorTextColor = p.mIndicatorTextColor;
        this.mIndicatorTextSize = p.mIndicatorTextSize;
        this.mIndicatorCustomView = p.mIndicatorCustomView;
        this.mIndicatorCustomTopContentView = p.mIndicatorCustomTopContentView;
        //track
        this.mBackgroundTrackSize = p.mBackgroundTrackSize;
        this.mProgressTrackSize = p.mProgressTrackSize;
        this.mBackgroundTrackColor = p.mBackgroundTrackColor;
        this.mProgressTrackColor = p.mProgressTrackColor;
        this.mTrackRoundedCorners = p.mTrackRoundedCorners;
        //tick
        this.mTickNum = p.mTickNum;
        this.mTickType = p.mTickType;
        this.mTickSize = p.mTickSize;
        this.mTickColor = p.mTickColor;
        this.mTickHideBothEnds = p.mTickHideBothEnds;
        this.mTickOnThumbLeftHide = p.mTickOnThumbLeftHide;
        this.mTickDrawable = p.mTickDrawable;
        //text
        this.mTextSize = p.mTextSize;
        this.mTextColor = p.mTextColor;
        this.mLeftEndText = p.mLeftEndText;
        this.mRightEndText = p.mRightEndText;
        this.mTextArray = p.mTextArray;
        this.mTextTypeface = p.mTextTypeface;
        //thumb
        this.mThumbColor = p.mThumbColor;
        this.mThumbSize = p.mThumbSize;
        this.mThumbDrawable = p.mThumbDrawable;
        this.mThumbProgressStay = p.mThumbProgressStay;
        return this;
    }
}
