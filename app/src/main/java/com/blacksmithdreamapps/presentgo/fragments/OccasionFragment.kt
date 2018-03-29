package com.blacksmithdreamapps.presentgo.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blacksmithdreamapps.presentgo.Constants
import com.blacksmithdreamapps.presentgo.R
import com.blacksmithdreamapps.presentgo.events.PagerPasserEvent
import kotlinx.android.synthetic.main.fragment_occasion.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.02.2018.
 */

class OccasionFragment : Fragment(), View.OnClickListener {


    // handling passing here viewPager via eventbus
    var bus = EventBus.getDefault()
    @Subscribe
    public fun setUpViewPager(event : PagerPasserEvent)
    {
        viewPager = event.viewPager
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bus.register(this)
    }

    override fun onClick(p0: View?) {

        if (p0 != null) {
            when (p0.id) {
                R.id.anniversary_cv -> {
                    editor.putString(Constants.OCCASION, Constants.OCCASION_ANNIVERSARY)
                    editor.apply()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.holiday_cv -> {
                    editor.putString(Constants.OCCASION, Constants.OCCASION_HOLIDAY)
                    editor.apply()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.birthday_cv -> {
                    editor.putString(Constants.OCCASION, Constants.OCCASION_BIRTHDAY)
                    editor.apply()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.graduation_cv -> {
                    editor.putString(Constants.OCCASION, Constants.OCCASION_GRADUATION)
                    editor.apply()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.wedding_cv -> {
                    editor.putString(Constants.OCCASION, Constants.OCCASION_WEDDING)
                    editor.apply()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.just_because_cv -> {
                    editor.putString(Constants.OCCASION, Constants.OCCASION_JUST_BECAUSE)
                    editor.apply()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
            }
        }
    }


    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_occasion, container, false)

        preferences = context.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
        editor = preferences.edit();

        view.anniversary_cv.setOnClickListener(this)
        view.holiday_cv.setOnClickListener(this)
        view.birthday_cv.setOnClickListener(this)
        view.graduation_cv.setOnClickListener(this)
        view.wedding_cv.setOnClickListener(this)
        view.just_because_cv.setOnClickListener(this)

        return view
    }

    companion object {
        fun newInstance(viewPager: ViewPager): OccasionFragment {
            val fragment = OccasionFragment()

            fragment.viewPager = viewPager
            return fragment
        }
    }
}// Required empty public constructor
