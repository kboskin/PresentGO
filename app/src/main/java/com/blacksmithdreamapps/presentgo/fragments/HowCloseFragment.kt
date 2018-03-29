package fragments

import android.annotation.SuppressLint
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
import com.blacksmithdreamapps.presentgo.seekbar.howclose.IndicatorSeekBar
import kotlinx.android.synthetic.main.fragment_how_close.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.02.2018.
 */

class HowCloseFragment : Fragment(), View.OnClickListener {


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
                R.id.button_done_how_close -> {
                    editor.putString(Constants.CLOSE, closeness)
                    editor.apply()
                    viewPager.currentItem = viewPager.currentItem + 1
                }
            }
        }
    }

    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var viewPager: ViewPager
    lateinit var closeness: String

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_how_close, container, false)
        val indicatorSeekBar: IndicatorSeekBar = view.findViewById(R.id.myOwnSeekBar)

        //default value
        closeness = Constants.CLOSE_ACQUAINTANCE

        preferences = context.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
        editor = preferences.edit();

        view.button_done_how_close.setOnClickListener(this)


        indicatorSeekBar.setOnSeekChangeListener(object : IndicatorSeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: IndicatorSeekBar?, thumbPosOnTick: Int) {

            }

            @SuppressLint("SetTextI18n")
            override fun onSectionChanged(seekBar: IndicatorSeekBar?, thumbPosOnTick: Int, textBelowTick: String?, fromUserTouch: Boolean) {
                when (thumbPosOnTick) {
                }

            }

            override fun onStopTrackingTouch(seekBar: IndicatorSeekBar?) {

            }

            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: IndicatorSeekBar?, progress: Int, progressFloat: Float, fromUserTouch: Boolean) {
                if (progress in 0..19) {
                    closeness = Constants.CLOSE_STRANGER
                } else if (progress in 20..39) {
                    closeness = Constants.CLOSE_ONE_TIME_SEEN
                } else if (progress in 40..59) {
                    closeness = Constants.CLOSE_ACQUAINTANCE
                } else if (progress in 60..79) {
                    closeness = Constants.CLOSE_COMRADE
                } else {
                    closeness = Constants.CLOSE_CLOSE_PERSON
                }
            }
        })
        return view
    }

    companion object {
        fun newInstance(viewPager: ViewPager): HowCloseFragment {
            val fragment = HowCloseFragment()
            fragment.viewPager = viewPager
            return fragment
        }
    }
}// Required empty public constructor
