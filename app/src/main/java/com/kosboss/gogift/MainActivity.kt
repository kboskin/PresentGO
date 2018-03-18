package com.kosboss.gogift

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.View
import android.widget.RelativeLayout
import com.google.android.gms.ads.*
import com.kosboss.gogift.events.PagerPasserEvent
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus


class MainActivity : AppCompatActivity() {
    lateinit var mAdView: AdView
    lateinit var viewPager: ViewPager
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
        }
        mInterstitialAd.loadAd(AdRequest.Builder().addTestDevice("4AB458BDDA5C649998AB1AA81B0EEE8E").build())


        viewPager = findViewById(R.id.pager);
        // deny swipes for view pager
        viewPager.setOnTouchListener(View.OnTouchListener { view, motionEvent -> return@OnTouchListener true })


        mTabLayout = findViewById(R.id.tabDots);
        mTabLayout.setupWithViewPager(viewPager, true);

        mTabLayout.setOnTouchListener(View.OnTouchListener { view, motionEvent -> return@OnTouchListener true })
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
}
