package com.kosboss.gogift.seekbar

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup

/**
 * created by ZhuangGuangquan on  2017/9/9
 */

object IndicatorUtils {
    fun dp2px(context: Context, dpValue: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.resources.displayMetrics).toInt()
    }

    fun sp2px(context: Context, spValue: Float): Int {
        return (spValue * context.resources.displayMetrics.scaledDensity + 0.5f).toInt()
    }

    fun px2sp(context: Context, pxValue: Float): Int {
        return (pxValue / context.resources.displayMetrics.scaledDensity + 0.5f).toInt()
    }


    fun isViewCovered(view: View): Boolean {
        var currentView = view

        val currentViewRect = Rect()
        val partVisible = currentView.getGlobalVisibleRect(currentViewRect)
        val totalHeightVisible = currentViewRect.bottom - currentViewRect.top >= view.measuredHeight
        val totalWidthVisible = currentViewRect.right - currentViewRect.left >= view.measuredWidth
        val totalViewVisible = partVisible && totalHeightVisible && totalWidthVisible
        if (!totalViewVisible)
            return true

        while (currentView.parent is ViewGroup) {
            val currentParent = currentView.parent as ViewGroup
            if (currentParent.visibility != View.VISIBLE)
                return true

            val start = indexOfViewInParent(currentView, currentParent)
            for (i in start + 1 until currentParent.childCount) {
                val viewRect = Rect()
                view.getGlobalVisibleRect(viewRect)
                val otherView = currentParent.getChildAt(i)
                val otherViewRect = Rect()
                otherView.getGlobalVisibleRect(otherViewRect)
                if (Rect.intersects(viewRect, otherViewRect))
                    return true
            }
            currentView = currentParent
        }
        return false
    }


    private fun indexOfViewInParent(view: View, parent: ViewGroup): Int {
        var index: Int
        index = 0
        while (index < parent.childCount) {
            if (parent.getChildAt(index) === view)
                break
            index++
        }
        return index
    }


}
