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
import com.kosboss.gogift.events.ImageChangeEvent
import kotlinx.android.synthetic.main.fragment_age.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class AgeFragment : Fragment(), View.OnClickListener {
    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.child_cv -> {
                    editor.putString(constants.AGE, constants.AGE_CHILD)
                    editor.commit()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.teenager_cv -> {
                    editor.putString(constants.AGE, constants.AGE_TEEN)
                    editor.commit()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.adult_cv -> {
                    editor.putString(constants.AGE, constants.AGE_ADULT)
                    editor.commit()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
                R.id.elderly_cv -> {
                    editor.putString(constants.AGE, constants.AGE_ELDERLY)
                    editor.commit()
                    viewPager.currentItem = viewPager.currentItem + 1

                }
            }
        }
    }

    lateinit var viewPager: ViewPager
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var constants: Constants
    lateinit var viewFrag: View
    var bus = EventBus.getDefault()


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_age, container, false)

        viewFrag = view

        constants = Constants()

        preferences = context.getSharedPreferences(constants.SHARED_PREFS, Context.MODE_PRIVATE)
        editor = preferences.edit();


        if (preferences.getString(constants.SEX, constants.SEX_FEMALE) == constants.SEX_MALE) {
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
        fun newInstance(viewPager: ViewPager, constants: Constants): AgeFragment {
            val fragment = AgeFragment()
            val args = Bundle()
            fragment.arguments = args
            fragment.constants = constants
            fragment.viewPager = viewPager
            return fragment
        }
    }
}// Required empty public constructor
