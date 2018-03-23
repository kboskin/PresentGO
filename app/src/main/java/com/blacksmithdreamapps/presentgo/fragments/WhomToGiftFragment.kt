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
import kotlinx.android.synthetic.main.persons_layout.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.02.2018.
 */

class WhomToGiftFragment : Fragment(), View.OnClickListener {
    lateinit var viewPager: ViewPager
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var constants: Constants
    var bus = EventBus.getDefault()

    // all nulls to manage values
    @Subscribe
    public fun setUpViewPager(event: PagerPasserEvent) {
        viewPager = event.viewPager
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bus.register(this)
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.family_cv -> {
                    preferences = context.getSharedPreferences(constants.SHARED_PREFS, Context.MODE_PRIVATE)
                    editor = preferences.edit();

                    editor.putString(constants.CATEGORY, constants.CATEGORY_FAMILY)
                    editor.commit()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.friends_cv -> {
                    preferences = context.getSharedPreferences(constants.SHARED_PREFS, Context.MODE_PRIVATE)
                    editor = preferences.edit();
                    editor.putString(constants.CATEGORY, constants.CATEGORY_FRIENDS)
                    editor.commit()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.colleagues_cv -> {
                    preferences = context.getSharedPreferences(constants.SHARED_PREFS, Context.MODE_PRIVATE)
                    editor = preferences.edit();
                    editor.putString(constants.CATEGORY, constants.CATEGORY_COLLEAGUES)
                    editor.commit()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.beloved_cv -> {
                    preferences = context.getSharedPreferences(constants.SHARED_PREFS, Context.MODE_PRIVATE)
                    editor = preferences.edit();
                    editor.putString(constants.CATEGORY, constants.CATEGORY_BELOVED)
                    editor.commit()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.superiors_cv -> {
                    preferences = context.getSharedPreferences(constants.SHARED_PREFS, Context.MODE_PRIVATE)
                    editor = preferences.edit();
                    editor.putString(constants.CATEGORY, constants.CATEGORY_SUPERIORS)
                    editor.commit()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.person_i_hate_cv -> {
                    preferences = context.getSharedPreferences(constants.SHARED_PREFS, Context.MODE_PRIVATE)
                    editor = preferences.edit();
                    editor.putString(constants.CATEGORY, constants.CATEGORY_HATED)
                    editor.commit()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_whom_to_gift, container, false)

        constants = Constants()

        view.family_cv.setOnClickListener(this)
        view.friends_cv.setOnClickListener(this)
        view.colleagues_cv.setOnClickListener(this)
        view.beloved_cv.setOnClickListener(this)
        view.superiors_cv.setOnClickListener(this)
        view.person_i_hate_cv.setOnClickListener(this)

        return view
    }


    companion object {

        fun newInstance(viewPager: ViewPager, constants: Constants): WhomToGiftFragment {
            val fragment = WhomToGiftFragment()
            fragment.viewPager = viewPager
            fragment.constants = constants
            return fragment
        }
    }
}