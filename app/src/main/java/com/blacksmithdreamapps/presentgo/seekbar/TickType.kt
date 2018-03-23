package com.blacksmithdreamapps.presentgo.seekbar


/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.02.2018.
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