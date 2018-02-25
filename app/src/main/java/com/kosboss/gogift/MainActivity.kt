package com.kosboss.gogift

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity() {
    lateinit var mAdView : AdView
    lateinit var mImageViewPager : ViewPager
    lateinit var mTabLayout : TabLayout
    lateinit var fm : FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this, "ca-app-pub-2631718830975455~9288409657")

        mAdView = findViewById(R.id.adView)
        // add here your device id, you can see it in logs
        val adRequest = AdRequest.Builder().addTestDevice("4AB458BDDA5C649998AB1AA81B0EEE8E").build()
        mAdView.loadAd(adRequest)

        mImageViewPager = findViewById(R.id.pager);
        mTabLayout = findViewById(R.id.tabDots);
        mTabLayout.setupWithViewPager(mImageViewPager, true);
        val pagesAdapter = PagesAdapter(supportFragmentManager);
        mImageViewPager.adapter = pagesAdapter



    }
}
