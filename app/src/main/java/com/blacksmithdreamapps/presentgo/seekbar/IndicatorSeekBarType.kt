package com.kosboss.gogift.seekbar


/**
 * created by ZhuangGuangquan on 2017/9/11
 */
interface IndicatorSeekBarType {
    companion object {
        /**
         * seek bar slides smoothly.
         */
        val CONTINUOUS = 0
        /**
         * seek bar with end side text slides smoothly.
         */
        val CONTINUOUS_TEXTS_ENDS = 1
        /**
         * seek bar with tick marks will  slide with a block length.
         */
        val DISCRETE_TICKS = 2
        /**
         * seek bar with tick marks and texts below marks will slide with a block length.
         */
        val DISCRETE_TICKS_TEXTS = 3
        /**
         * seek bar with tick marks and texts below marks of both end sides will  slide with a block length.
         */
        val DISCRETE_TICKS_TEXTS_ENDS = 4
    }
}