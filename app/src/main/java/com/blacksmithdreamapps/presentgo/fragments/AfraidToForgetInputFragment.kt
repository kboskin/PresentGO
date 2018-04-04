package com.blacksmithdreamapps.presentgo.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blacksmithdreamapps.presentgo.R
import com.blacksmithdreamapps.presentgo.activities.GiftActivity
import com.blacksmithdreamapps.presentgo.events.DataPassingEvent
import com.blacksmithdreamapps.presentgo.events.PagerPasserEvent
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.fragment_fragment_afraid_to_forget_input.*
import kotlinx.android.synthetic.main.fragment_fragment_afraid_to_forget_input.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*


/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.02.2018.
 */

class AfraidToForgetInputFragment : Fragment() {

    // handling passing here viewPager via eventbus
    var bus = EventBus.getDefault()

    private lateinit var mInterstitialAd: InterstitialAd

    @Subscribe
    public fun setUpViewPager(event: PagerPasserEvent) {
        viewPager = event.viewPager
    }

    @Subscribe
    public fun setUpTime(event: DataPassingEvent) {
        time = event.millisTime
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bus.register(this)
    }

    lateinit var viewPager: ViewPager
    var time: Long = 0
    var triggerForAds: Boolean? = true

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // admob
        MobileAds.initialize(context, "ca-app-pub-2631718830975455~9288409657")

        mInterstitialAd = InterstitialAd(context)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.adListener = object : AdListener() {

            override fun onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(AdRequest.Builder().addTestDevice("4AB458BDDA5C649998AB1AA81B0EEE8E").build())
                startActivity(Intent(context, GiftActivity::class.java))
            }

            override fun onAdFailedToLoad(p0: Int) {
                triggerForAds = false
            }
        }

        mInterstitialAd.loadAd(AdRequest.Builder().addTestDevice("4AB458BDDA5C649998AB1AA81B0EEE8E").build())


        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_fragment_afraid_to_forget_input, container, false)
        // skip calendar integration and show fragment
        view.show_me_gift.setOnClickListener {
            if (mInterstitialAd.isLoaded) {
                mInterstitialAd.show()
            } else {
                if (triggerForAds == false) {
                    // value to start activity by click of button
                    startActivity(Intent(context, GiftActivity::class.java))

                }
            }
        }
        view.button_done_finally.setOnClickListener {

            val intent = Intent(Intent.ACTION_EDIT)
            intent.type = "vnd.android.cursor.item/event"

            // sometimes for some reasons not working
            intent.putExtra(CalendarContract.Events.TITLE, edit_text_title_real.text.toString())
            intent.putExtra(CalendarContract.Events.DESCRIPTION, edit_text_title_real.text.toString())

            // all day event
            intent.putExtra(CalendarContract.Events.ALL_DAY, true);

            // every year
            intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, time);
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, time + 60 * 60 * 1000);

            // for favourite one
            intent.putExtra(CalendarContract.Events.CALENDAR_ID, 1)
            // setting alarm
            intent.putExtra(CalendarContract.Events.HAS_ALARM, true)
            // time zone
            intent.putExtra(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault())
            startActivity(intent)
        }
        return view
    }

    companion object {
        fun newInstance(viewPager: ViewPager): AfraidToForgetInputFragment {
            val fragment = AfraidToForgetInputFragment()
            fragment.viewPager = viewPager
            return fragment
        }
    }
}// Required empty public constructor
