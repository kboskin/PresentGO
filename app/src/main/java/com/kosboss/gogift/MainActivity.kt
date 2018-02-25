package com.kosboss.gogift

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity() {
    lateinit var mAdView : AdView
    lateinit var viewPager: ViewPager
    lateinit var mTabLayout : TabLayout
    lateinit var fm : FragmentManager


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this, "ca-app-pub-2631718830975455~9288409657")

        mAdView = findViewById(R.id.adView)
        // add here your device id, you can see it in logs
        val adRequest = AdRequest.Builder().addTestDevice("4AB458BDDA5C649998AB1AA81B0EEE8E").build()
        mAdView.loadAd(adRequest)

        viewPager = findViewById(R.id.pager);
        // deni swipes for view pager
        viewPager.setOnTouchListener(View.OnTouchListener{view, motionEvent -> return@OnTouchListener true})
        mTabLayout = findViewById(R.id.tabDots);
        mTabLayout.setupWithViewPager(viewPager, true);
        val pagesAdapter = PagesAdapter(supportFragmentManager);
        viewPager.adapter = pagesAdapter



    }
}
