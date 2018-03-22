package com.kosboss.gogift.seekbar

/**
 * created by ZhuangGuangquan on 2017/9/11
 */

interface TickType {
    companion object {
        /**
         * not show tickMarks
         */
        val NONE = 0
        /**
         * show tickMarks shape as regular rectangle
         */
        val REC = 1
        /**
         * show tickMarks shape as regular oval
         */
        val OVAL = 2
    }
}