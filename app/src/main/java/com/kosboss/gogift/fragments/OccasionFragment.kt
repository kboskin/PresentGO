package com.kosboss.gogift.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kosboss.gogift.Constants
import com.kosboss.gogift.R
import com.kosboss.gogift.events.PagerPasserEvent
import kotlinx.android.synthetic.main.fragment_occasion.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

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
                    editor.putString(constants.OCCASION, constants.OCCASION_ANNIVERSARY)
                    editor.apply()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.holiday_cv -> {
                    editor.putString(constants.OCCASION, constants.OCCASION_HOLIDAY)
                    editor.apply()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.birthday_cv -> {
                    editor.putString(constants.OCCASION, constants.OCCASION_BIRTHDAY)
                    editor.apply()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.graduation_cv -> {
                    editor.putString(constants.OCCASION, constants.OCCASION_GRADUATION)
                    editor.apply()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.wedding_cv -> {
                    editor.putString(constants.OCCASION, constants.OCCASION_WEDDING)
                    editor.apply()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.just_because_cv -> {
                    editor.putString(constants.OCCASION, constants.OCCASION_JUST_BECAUSE)
                    editor.apply()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
            }
        }
    }


    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var constants: Constants
    lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_occasion, container, false)

        constants = Constants()

        preferences = context.getSharedPreferences(constants.SHARED_PREFS, Context.MODE_PRIVATE)
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
        fun newInstance(viewPager: ViewPager, constants: Constants): OccasionFragment {
            val fragment = OccasionFragment()

            fragment.constants = constants
            fragment.viewPager = viewPager
            return fragment
        }
    }
}// Required empty public constructor