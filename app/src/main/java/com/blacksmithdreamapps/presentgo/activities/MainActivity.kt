package com.blacksmithdreamapps.presentgo.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import com.blacksmithdreamapps.presentgo.Constants
import com.blacksmithdreamapps.presentgo.R
import com.blacksmithdreamapps.presentgo.events.PagerPasserEvent
import com.blacksmithdreamapps.presentgo.settings.SettingsActivity
import com.blacksmithdreamapps.presentgo.viewpager.NonSwipableViewPager
import com.google.android.gms.ads.*
import com.kosboss.gogift.viewpager.PagesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig

/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 025 25.02.2018
 */


class MainActivity : AppCompatActivity() {
    lateinit var mAdView: AdView
    lateinit var viewPager: NonSwipableViewPager
    lateinit var mTabLayout: TabLayout
    lateinit var previousButton: RelativeLayout
    lateinit var forwardButton: RelativeLayout
    private lateinit var mInterstitialAd: InterstitialAd


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // toolbar, title is empty
        main_tool_bar.title = ""
        // setting custom image instead of three dots
        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.setting_shape)
        main_tool_bar.overflowIcon = drawable
        setSupportActionBar(main_tool_bar)

        MobileAds.initialize(this, "ca-app-pub-2631718830975455~9288409657")

        // setting default preferences
        setDefaultPrefs()
        // admob
        mAdView = findViewById(R.id.adView)
        // add here your device id, you can see it in logs
        val adRequest = AdRequest.Builder().addTestDevice("4AB458BDDA5C649998AB1AA81B0EEE8E").build()
        mAdView.loadAd(adRequest)

        // banner between pages
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.adListener = object : AdListener() {

            override fun onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(AdRequest.Builder().addTestDevice("4AB458BDDA5C649998AB1AA81B0EEE8E").build())
                startActivity(Intent(this@MainActivity, GiftActivity::class.java))
            }

            override fun onAdFailedToLoad(p0: Int) {
                startActivity(Intent(this@MainActivity, GiftActivity::class.java))
            }
        }
        mInterstitialAd.loadAd(AdRequest.Builder().addTestDevice("4AB458BDDA5C649998AB1AA81B0EEE8E").build())

        // swipe-denied view pager
        viewPager = findViewById(R.id.pager);
        //viewPager.setOnTouchListener(View.OnTouchListener { view, motionEvent -> return@OnTouchListener true })


        mTabLayout = findViewById(R.id.tabDots);
        mTabLayout.setupWithViewPager(viewPager, true);

        mTabLayout.setOnTouchListener(View.OnTouchListener { view, motionEvent -> return@OnTouchListener false })
        val pagesAdapter = PagesAdapter(supportFragmentManager, viewPager);
        viewPager.adapter = pagesAdapter

        // pass viewPager
        val bus = EventBus.getDefault()
        bus.post(object : PagerPasserEvent(viewPager) {})

        // back click listener
        previousButton = findViewById(R.id.back_ui)
        previousButton.setOnClickListener { viewPager.currentItem = viewPager.currentItem - 1 }

        // forward click listener
        forwardButton = findViewById(R.id.forward_ui)
        forwardButton.setOnClickListener {
            if (viewPager.currentItem == 7) {
                if (mInterstitialAd.isLoaded) {
                    mInterstitialAd.show()
                } else {
                }
                Log.d("Pager item", viewPager.currentItem.toString())
            } else {
                viewPager.currentItem = viewPager.currentItem + 1
                Log.d("Pager item", viewPager.currentItem.toString())
            }

        }
        setTutorial()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        var inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_setting -> {
                startActivity(Intent(MainActivity@ this, SettingsActivity::class.java));
                return true
            }
        /*R.id.help -> {
            showHelp()
            return true
        }*/
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun setDefaultPrefs() {
        var constants = Constants()
        val prefs = getSharedPreferences(constants.SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = prefs.edit();
        editor.putString(constants.CLOSE, "")
        editor.putString(constants.OCCASION, "")
        editor.putString(constants.BUDGET, "")
        editor.putString(constants.AGE, "")
        editor.putString(constants.CATEGORY, "")
        editor.putString(constants.SEX, "")
        editor.apply()
    }

    private fun setTutorial() {
        // sequence example
        val config = ShowcaseConfig()
        config.delay = 500 // half second between each showcase view
        config.dismissTextColor = resources.getColor(R.color.colorGreenBackground) // green text
        config.fadeDuration = 700 // fade anim duration

        val sequence = MaterialShowcaseSequence(this)

        sequence.setConfig(config)

        sequence.addSequenceItem(forwardButton,
                getString(R.string.button_back), getString(R.string.got_it_text))

        sequence.addSequenceItem(previousButton,
                getString(R.string.button_skip), getString(R.string.got_it_text))

        sequence.addSequenceItem(main_tool_bar,
                getString(R.string.title_descr_etc), getString(R.string.got_it_text))

        sequence.start()
    }
}
