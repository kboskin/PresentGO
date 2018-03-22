package com.kosboss.gogift.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kosboss.gogift.R
import com.kosboss.gogift.activities.GiftActivity
import com.kosboss.gogift.events.DataPassingEvent
import com.kosboss.gogift.events.PagerPasserEvent
import kotlinx.android.synthetic.main.fragment_fragment_afraid_to_forget_input.*
import kotlinx.android.synthetic.main.fragment_fragment_afraid_to_forget_input.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AfraidToForgetInputFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AfraidToForgetInputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AfraidToForgetInputFragment : Fragment() {

    // handling passing here viewPager via eventbus
    var bus = EventBus.getDefault()

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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_fragment_afraid_to_forget_input, container, false)
        // skip calendar integration and show fragment
        view.show_me_gift.setOnClickListener{
            startActivity(Intent(context, GiftActivity::class.java))
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
            val args = Bundle()
            fragment.viewPager = viewPager
            return fragment
        }
    }
}// Required empty public constructor
