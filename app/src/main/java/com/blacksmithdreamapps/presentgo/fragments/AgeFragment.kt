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
import com.blacksmithdreamapps.presentgo.events.ImageChangeEvent
import kotlinx.android.synthetic.main.fragment_age.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.02.2018.
 */

class AgeFragment : Fragment(), View.OnClickListener {

    lateinit var viewPager: ViewPager
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var viewFrag: View
    var bus = EventBus.getDefault()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_age, container, false)

        viewFrag = view

        preferences = context.getSharedPreferences(com.blacksmithdreamapps.presentgo.Constants.SHARED_PREFS, Context.MODE_PRIVATE)
        editor = preferences.edit();


        if (preferences.getString(Constants.SEX, Constants.SEX_FEMALE) == Constants.SEX_MALE) {
            view.child_cv_image.setImageResource(R.drawable.baby_boy)
            view.teenager_cv_image.setImageResource(R.drawable.teen_boy)
            view.adult_cv_image.setImageResource(R.drawable.adult_man)
            view.elderly_cv_image.setImageResource(R.drawable.grandfather)
        }
        view.child_cv.setOnClickListener(this)
        view.teenager_cv.setOnClickListener(this)
        view.adult_cv.setOnClickListener(this)
        view.elderly_cv.setOnClickListener(this)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bus.register(this)
    }

    //catch Event from fragment A
    @Subscribe
    public fun imageChangeEvent(event: ImageChangeEvent) {
        if (event.imageType == 1) {
            viewFrag.child_cv_image.setImageResource(R.drawable.baby_boy)
            viewFrag.teenager_cv_image.setImageResource(R.drawable.teen_boy)
            viewFrag.adult_cv_image.setImageResource(R.drawable.adult_man)
            viewFrag.elderly_cv_image.setImageResource(R.drawable.grandfather)
        } else {
            viewFrag.child_cv_image.setImageResource(R.drawable.baby_girl)
            viewFrag.teenager_cv_image.setImageResource(R.drawable.teen_girl)
            viewFrag.adult_cv_image.setImageResource(R.drawable.adult_woman)
            viewFrag.elderly_cv_image.setImageResource(R.drawable.grandmother)
        }
    }

    companion object {
        fun newInstance(viewPager: ViewPager): AgeFragment {
            val fragment = AgeFragment()
            val args = Bundle()
            fragment.arguments = args
            fragment.viewPager = viewPager
            return fragment
        }
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.child_cv -> {
                    editor.putString(com.blacksmithdreamapps.presentgo.Constants.AGE, com.blacksmithdreamapps.presentgo.Constants.AGE_CHILD)
                    editor.commit()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.teenager_cv -> {
                    editor.putString(com.blacksmithdreamapps.presentgo.Constants.AGE, com.blacksmithdreamapps.presentgo.Constants.AGE_TEEN)
                    editor.commit()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.adult_cv -> {
                    editor.putString(com.blacksmithdreamapps.presentgo.Constants.AGE, com.blacksmithdreamapps.presentgo.Constants.AGE_ADULT)
                    editor.commit()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.elderly_cv -> {
                    editor.putString(com.blacksmithdreamapps.presentgo.Constants.AGE, com.blacksmithdreamapps.presentgo.Constants.AGE_ELDERLY)
                    editor.commit()
                    viewPager.currentItem = viewPager.currentItem + 1

                }
            }
        }
    }

}// Required empty public constructor
