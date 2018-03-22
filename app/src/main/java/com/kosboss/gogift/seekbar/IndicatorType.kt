package com.kosboss.gogift.seekbar

/**
 * created by ZhuangGuangquan on  2017/9/9
 */

interface IndicatorType {
    companion object {
        /**
         * the indicator corners is square shape
         */
        val RECTANGLE = 0
        /**
         * the indicator corners is rounded shape
         */
        val RECTANGLE_ROUNDED_CORNER = 1
        /**
         * the indicator shape like water-drop
         */
        val CIRCULAR_BUBBLE = 2
        /**
         * set custom indicator you want.
         */
        val CUSTOM = 3
    }

}