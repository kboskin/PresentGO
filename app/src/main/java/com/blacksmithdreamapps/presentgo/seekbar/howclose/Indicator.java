package com.blacksmithdreamapps.presentgo.seekbar.howclose;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blacksmithdreamapps.presentgo.R;
import com.blacksmithdreamapps.presentgo.seekbar.ArrowView;
import com.blacksmithdreamapps.presentgo.seekbar.BuilderParams;
import com.blacksmithdreamapps.presentgo.seekbar.CircleBubbleView;
import com.blacksmithdreamapps.presentgo.seekbar.HowToChangeTheText;
import com.blacksmithdreamapps.presentgo.seekbar.IndicatorSeekBarType;
import com.blacksmithdreamapps.presentgo.seekbar.IndicatorType;
import com.blacksmithdreamapps.presentgo.seekbar.IndicatorUtils;


/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.02.2018.
 */

public class Indicator implements HowToChangeTheText {
    private final Context mContext;
    private final IndicatorSeekBar mSeekBar;
    private final int mWindowWidth;
    private int[] mLocation = new int[2];
    private ArrowView mIndicatorArrow;
    private TextView mIndicatorText;
    private PopupWindow mIndicator;
    private View mIndicatorView;
    private LinearLayout mTopContentView;
    private int mGap;
    private BuilderParams p;

    public Indicator(Context context, IndicatorSeekBar seekBar, BuilderParams p) {
        this.mContext = context;
        this.mSeekBar = seekBar;
        this.p = p;
        initIndicator();
        mWindowWidth = getWindowWidth();
        mGap = IndicatorUtils.INSTANCE.dp2px(mContext, 2);
    }

    void initIndicator() {
        if (p.getMIndicatorType() == IndicatorType.Companion.getCUSTOM()) {
            if (p.getMIndicatorCustomView() != null) {
                mIndicatorView = p.getMIndicatorCustomView();
                //for the custom indicator view, if progress need to show when seeking ,
                // need a TextView to show progress and this textView 's identify must be progress;
                int progressTextViewId = mContext.getResources().getIdentifier("isb_progress_continious", "id", mContext.getApplicationContext().getPackageName());
                if (progressTextViewId > 0) {
                    View view = mIndicatorView.findViewById(progressTextViewId);
                    if (view != null) {
                        if (view instanceof TextView) {
                            //progressText
                            mIndicatorText = (TextView) view;

                        } else {
                            throw new ClassCastException("the view identified by isb_progress in indicator custom layout can not be cast to TextView");
                        }
                    }
                }
            }
        } else {
            if (IndicatorType.Companion.getCIRCULAR_BUBBLE() == p.getMIndicatorType()) {
                mIndicatorView = new CircleBubbleView(p, checkHolderText());
                ((CircleBubbleView) mIndicatorView).setProgress(String.valueOf(mSeekBar.getProgress()));
            } else {
                mIndicatorView = View.inflate(mContext, R.layout.isb_indicator_continious, null);
                //container
                mTopContentView = (LinearLayout) mIndicatorView.findViewById(R.id.indicator_container);
                //arrow
                mIndicatorArrow = (ArrowView) mIndicatorView.findViewById(R.id.indicator_arrow);
                mIndicatorArrow.setColor(p.getMIndicatorColor());
                //progressText
                mIndicatorText = (TextView) mIndicatorView.findViewById(R.id.isb_progress_continious);


                mIndicatorText.setTextSize(IndicatorUtils.INSTANCE.px2sp(mContext, p.getMIndicatorTextSize()));
                mIndicatorText.setTextColor(p.getMIndicatorTextColor());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mTopContentView.setBackground(getGradientDrawable());
                } else {
                    mTopContentView.setBackgroundDrawable(getGradientDrawable());
                }
                //custom top content view
                if (p.getMIndicatorCustomTopContentView() != null) {
                    //for the custom indicator top content view, if progress need to show when seeking ,
                    //need a TextView to show progress and this textView 's identify must be progress;
                    int progressTextViewId = mContext.getResources().getIdentifier("isb_progress_continious", "id", mContext.getApplicationContext().getPackageName());
                    View topContentView = p.getMIndicatorCustomTopContentView();
                    if (progressTextViewId > 0) {
                        View tv = topContentView.findViewById(progressTextViewId);
                        if (tv != null) {
                            setIndicatorTopContentView(topContentView, progressTextViewId);
                        } else {
                            setIndicatorTopContentView(topContentView);
                        }
                    } else {
                        setIndicatorTopContentView(topContentView);
                    }

                }
            }
        }
        if (mIndicatorView != null) {
            mIndicatorView.measure(0, 0);
            mIndicator = new PopupWindow(mIndicatorView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, false);
        }
    }

    String checkHolderText() {
        if (p.getMSeekBarType() == IndicatorSeekBarType.Companion.getCONTINUOUS() || p.getMSeekBarType() == IndicatorSeekBarType.Companion.getCONTINUOUS_TEXTS_ENDS()) {
            String maxString = String.valueOf(p.getMMax());
            String minString = String.valueOf(p.getMMin());
            return maxString.getBytes().length > minString.getBytes().length ? maxString : minString;
        } else {
            if (p.getMTextArray() != null) {
                String maxLengthText = "j";
                for (CharSequence c : p.getMTextArray()) {
                    if (c.length() > maxLengthText.length()) {
                        maxLengthText = c + "";
                    }
                }
                return maxLengthText;
            }
        }
        return "100";
    }

    @NonNull
    private GradientDrawable getGradientDrawable() {
        GradientDrawable tvDrawable;
        if (p.getMIndicatorType() == IndicatorType.Companion.getRECTANGLE_ROUNDED_CORNER()) {
            tvDrawable = (GradientDrawable) mContext.getResources().getDrawable(R.drawable.isb_indicator_square_corners);
        } else {
            tvDrawable = (GradientDrawable) mContext.getResources().getDrawable(R.drawable.isb_indicator_rounded_corners);
        }
        tvDrawable.setColor(p.getMIndicatorColor());
        return tvDrawable;
    }

    private int getWindowWidth() {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            return wm.getDefaultDisplay().getWidth();
        }
        return 0;
    }

    private int getIndicatorScreenX() {
        mSeekBar.getLocationOnScreen(mLocation);
        return mLocation[0];
    }

    private void adjustArrow(float touchX) {
        if (p.getMIndicatorType() == IndicatorType.Companion.getCUSTOM() || p.getMIndicatorType() == IndicatorType.Companion.getCIRCULAR_BUBBLE()) {
            return;
        }
        int indicatorScreenX = getIndicatorScreenX();
        if (indicatorScreenX + touchX < mIndicator.getContentView().getMeasuredWidth() / 2) {
            setMargin(-(int) (mIndicator.getContentView().getMeasuredWidth() / 2 - indicatorScreenX - touchX), -1, -1, -1);
        } else if (mWindowWidth - indicatorScreenX - touchX < mIndicator.getContentView().getMeasuredWidth() / 2) {
            setMargin((int) (mIndicator.getContentView().getMeasuredWidth() / 2 - (mWindowWidth - indicatorScreenX - touchX)), -1, -1, -1);
        } else {
            setMargin(0, 0, 0, 0);
        }
    }

    private void setMargin(int left, int top, int right, int bottom) {
        if (mIndicatorArrow == null) {
            return;
        }
        if (mIndicatorArrow.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mIndicatorArrow.getLayoutParams();
            layoutParams.setMargins(left == -1 ? layoutParams.leftMargin : left, top == -1 ? layoutParams.topMargin : top, right == -1 ? layoutParams.rightMargin : right, bottom == -1 ? layoutParams.bottomMargin : bottom);
            mIndicatorArrow.requestLayout();
        }
    }

    /**
     * call this to update indicator's location. if SeekBar is covered ,the indicator will dismiss auto and would show after the SeekBar showing completed.
     */
    public void update() {
        if (!mSeekBar.isEnabled() || !(mSeekBar.getVisibility() == View.VISIBLE)) {
            return;
        }
        if (mSeekBar.isCover()) {
            this.forceHide();
        } else {
            if (mSeekBar.getVisibility() == View.VISIBLE) {
                if (this.isShowing()) {
                    this.update(mSeekBar.getTouchX());
                } else {
                    this.show(mSeekBar.getTouchX());
                }
            }
        }
    }

    /**
     * update the indicator position
     *
     * @param touchX the x location you touch without padding left.
     */
    void update(float touchX) {
        if (!mSeekBar.isEnabled() || !(mSeekBar.getVisibility() == View.VISIBLE)) {
            return;
        }
        if (mIndicatorView instanceof CircleBubbleView) {
            ((CircleBubbleView) mIndicatorView).setProgress(mSeekBar.getProgressString());
        } else if (mIndicatorText != null) {
            changeText();
            mIndicator.getContentView().measure(0, 0);
        }
        mIndicator.update(mSeekBar, (int) (touchX - mIndicator.getContentView().getMeasuredWidth() / 2), -(mSeekBar.getMeasuredHeight() + mIndicator.getContentView().getMeasuredHeight() - mSeekBar.getPaddingTop() + mGap), -1, -1);
        adjustArrow(touchX);
    }

    /**
     * call this to show indicator
     */
    public void show() {
        if (!mSeekBar.isEnabled() || !(mSeekBar.getVisibility() == View.VISIBLE)) {
            return;
        }
        if (!this.isShowing() && !mSeekBar.isCover()) {
            this.show(mSeekBar.getTouchX());
        }
    }

    /**
     * call this method to show the indicator.
     *
     * @param touchX the x location you touch without padding left.
     */

    void show(float touchX) {
        if (mIndicator.isShowing() || !mSeekBar.isEnabled() || !(mSeekBar.getVisibility() == View.VISIBLE)) {
            return;
        }
        if (mIndicatorView instanceof CircleBubbleView) {
            ((CircleBubbleView) mIndicatorView).setProgress(mSeekBar.getProgressString());
        } else if (mIndicatorText != null) {
            changeText();
            mIndicator.getContentView().measure(0, 0);
        }
        mIndicator.showAsDropDown(mSeekBar, (int) (touchX - mIndicator.getContentView().getMeasuredWidth() / 2f), -(mSeekBar.getMeasuredHeight() + mIndicator.getContentView().getMeasuredHeight() - mSeekBar.getPaddingTop() + mGap));
        adjustArrow(touchX);
    }

    /**
     * call this method hide the indicator
     */
    public void hide() {
        if (mIndicator.isShowing()) {
            if (!p.getMIndicatorStay()) {
                mIndicator.dismiss();
            }
        }
    }

    /**
     * call this method to hide the indicator ignore indicator stay always.
     */
    public void forceHide() {
        if (mIndicator.isShowing()) {
            mIndicator.dismiss();
        }
    }

    /**
     * set the  View to the indicator top container, not influence indicator arrow ;
     * if indicator type is custom , this method will be not work.
     *
     * @param topContentView the view is inside the indicator TOP part, not influence indicator arrow;
     */
    public void setIndicatorTopContentView(@NonNull View topContentView) {
        mTopContentView.removeAllViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            topContentView.setBackground(getGradientDrawable());
        } else {
            topContentView.setBackgroundDrawable(getGradientDrawable());
        }
        mTopContentView.addView(topContentView);
    }

    /**
     * set the  View to the indicator top container, not influence indicator arrow ;
     * if indicator type is custom , this method will be not work.
     *
     * @param topContentLayoutId the view id for indicator TOP part, not influence indicator arrow;
     */
    public void setIndicatorTopContentLayout(@LayoutRes int topContentLayoutId) {
        mTopContentView.removeAllViews();
        View topContentView = View.inflate(mContext, topContentLayoutId, null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            topContentView.setBackground(getGradientDrawable());
        } else {
            topContentView.setBackgroundDrawable(getGradientDrawable());
        }
        mTopContentView.addView(topContentView);
    }

    /**
     * get the indicator content view.
     *
     * @return the view which is inside indicator.
     */
    public View getmContentView() {
        return mIndicator.getContentView();
    }

    /**
     * set the  View to the indicator top container, and show the changing progress in indicator when seek;
     * not influence indicator arrow;
     * * if indicator type is custom , this method will be not work.
     *
     * @param topContentView     the view is inside the indicator TOP part, not influence indicator arrow;
     * @param progressTextViewId the id can be find in @param topContentView, and it is a TextView id.
     */
    public void setIndicatorTopContentView(@NonNull View topContentView, @IdRes int progressTextViewId) {
        View tv = topContentView.findViewById(progressTextViewId);
        if (tv == null) {
            throw new IllegalArgumentException(" can not find the TextView in indicator topContentView by id: " + progressTextViewId);
        }
        if (!(tv instanceof TextView)) {
            throw new ClassCastException(" the view identified by progressTextViewId can not be cast to TextView. ");
        }
        mIndicatorText = (TextView) tv;
        mTopContentView.removeAllViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            topContentView.setBackground(getGradientDrawable());
        } else {
            topContentView.setBackgroundDrawable(getGradientDrawable());
        }
        mTopContentView.addView(topContentView);
    }

    /**
     * check the indicator is showing or not .
     *
     * @return true is showing.
     */
    public boolean isShowing() {
        return mIndicator.isShowing();
    }

    /**
     * call this method to replace the current indicator with a new indicator view , indicator arrow will be replace ,too.
     *
     * @param customIndicatorView a new view for indicator.
     */
    public void setCustomIndicator(@NonNull View customIndicatorView) {
        mIndicator.setContentView(customIndicatorView);
    }

    /**
     * the text view to show the progress in indicator , must can be found in indicator root view.
     *
     * @param view a text view can be found in indicator root view
     */
    public void setProgressTextView(@NonNull TextView view) {
        mIndicatorText = view;
    }

    @Override
    public void changeText() {
        String[] array = mContext.getResources().getStringArray(R.array.statuses);
        Log.d("CustomIndicator", "here is where custom indicator is working");
        if (mSeekBar.getProgress() <= 20) {
            mIndicatorText.setText(array[0]);
            mIndicatorText.setTextSize(IndicatorUtils.INSTANCE.px2sp(mContext, p.getMIndicatorTextSize()));
            mIndicatorText.setTextColor(p.getMIndicatorTextColor());
        } else if (mSeekBar.getProgress() > 20 && mSeekBar.getProgress() <= 40) {
            mIndicatorText.setText(array[1]);
            mIndicatorText.setTextSize(IndicatorUtils.INSTANCE.px2sp(mContext, p.getMIndicatorTextSize()));
            mIndicatorText.setTextColor(p.getMIndicatorTextColor());
        } else if (mSeekBar.getProgress() > 40 && mSeekBar.getProgress() <= 60) {
            mIndicatorText.setText(array[2]);
            mIndicatorText.setTextSize(IndicatorUtils.INSTANCE.px2sp(mContext, p.getMIndicatorTextSize()));
            mIndicatorText.setTextColor(p.getMIndicatorTextColor());
        } else if (mSeekBar.getProgress() > 60 && mSeekBar.getProgress() <= 80) {
            mIndicatorText.setText(array[3]);
            mIndicatorText.setTextSize(IndicatorUtils.INSTANCE.px2sp(mContext, p.getMIndicatorTextSize()));
            mIndicatorText.setTextColor(p.getMIndicatorTextColor());
        } else if (mSeekBar.getProgress() > 80 && mSeekBar.getProgress() <= 100) {
            mIndicatorText.setText(array[4]);
            mIndicatorText.setTextSize(IndicatorUtils.INSTANCE.px2sp(mContext, p.getMIndicatorTextSize()));
            mIndicatorText.setTextColor(p.getMIndicatorTextColor());
        }

    }
}