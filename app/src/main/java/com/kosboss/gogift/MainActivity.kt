package com.kosboss.gogift

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener


class MainActivity : AppCompatActivity(), RewardedVideoAdListener {
    lateinit var mAdView: AdView
    lateinit var viewPager: ViewPager
    lateinit var mTabLayout: TabLayout
    lateinit var previousButton: RelativeLayout
    lateinit var forwardButton: RelativeLayout
    private lateinit var mRewardedVideoAd: RewardedVideoAd


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this, "ca-app-pub-2631718830975455~9288409657")

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this@MainActivity)
        mRewardedVideoAd.rewardedVideoAdListener = this@MainActivity


        mAdView = findViewById(R.id.adView)
        // add here your device id, you can see it in logs
        val adRequest = AdRequest.Builder().addTestDevice("4AB458BDDA5C649998AB1AA81B0EEE8E").build()
        mAdView.loadAd(adRequest)

        viewPager = findViewById(R.id.pager);
        // deny swipes for view pager
        viewPager.setOnTouchListener(View.OnTouchListener { view, motionEvent -> return@OnTouchListener true })

        mTabLayout = findViewById(R.id.tabDots);
        mTabLayout.setupWithViewPager(viewPager, true);
        mTabLayout.setOnTouchListener(View.OnTouchListener { view, motionEvent -> return@OnTouchListener true })
        val pagesAdapter = PagesAdapter(supportFragmentManager, viewPager);
        viewPager.adapter = pagesAdapter

        // back click listener
        previousButton = findViewById(R.id.back_ui)
        previousButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                viewPager.setCurrentItem(viewPager.currentItem - 1)
            }
        })

        // forward click listener
        forwardButton = findViewById(R.id.forward_ui)
        forwardButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (viewPager.currentItem == 6) {

                    loadRewardedVideoAd()
                }
                viewPager.setCurrentItem(viewPager.currentItem + 1)
            }
        })


    }

    private fun loadRewardedVideoAd() {

        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                AdRequest.Builder().build())
    }

    override fun onPause() {
        super.onPause()
        mRewardedVideoAd.pause(this)
    }

    override fun onResume() {
        super.onResume()
        mRewardedVideoAd.resume(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mRewardedVideoAd.destroy(this)
    }

    override fun onRewarded(reward: RewardItem) {
        Toast.makeText(this, "onRewarded! currency: ${reward.type} amount: ${reward.amount}",
                Toast.LENGTH_SHORT).show()
        // Reward the user.
    }

    override fun onRewardedVideoAdLeftApplication() {
        //Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdClosed() {
        //Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {
        //Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoAdLoaded() {
        //Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show()
        mRewardedVideoAd.show()
    }

    override fun onRewardedVideoAdOpened() {
        //Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show()
    }

    override fun onRewardedVideoStarted() {
        //Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show()
    }
}
