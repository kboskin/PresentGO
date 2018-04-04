package com.blacksmithdreamapps.presentgo.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.blacksmithdreamapps.presentgo.activities.settings.SettingsActivity
import com.blacksmithdreamapps.presentgo.events.PagerPasserEvent
import com.blacksmithdreamapps.presentgo.viewpager.NonSwipableViewPager
import com.blacksmithdreamapps.presentgo.viewpager.PagesAdapter
import com.google.android.gms.ads.*
import com.stepstone.apprating.AppRatingDialog
import com.stepstone.apprating.listener.RatingDialogListener
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig
import java.util.*


/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 025 25.02.2018
 */


class MainActivity : AppCompatActivity(), RatingDialogListener {
    lateinit var mAdView: AdView
    lateinit var viewPager: NonSwipableViewPager
    lateinit var mTabLayout: TabLayout
    lateinit var previousButton: RelativeLayout
    lateinit var forwardButton: RelativeLayout
    private lateinit var mInterstitialAd: InterstitialAd
    private var triggerForAds: Boolean? = true

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
                Log.d("ishere", "ad was closed")
            }

            override fun onAdFailedToLoad(p0: Int) {
                super.onAdFailedToLoad(p0);
                Log.d("ishere", "failed to load")
                // value to start activity by click of button
                triggerForAds = false
                //startActivity(Intent(this@MainActivity, GiftActivity::class.java))
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
                    if (triggerForAds == false) {
                        startActivity(Intent(this, GiftActivity::class.java))
                    }
                }
            } else {
                viewPager.currentItem = viewPager.currentItem + 1
            }
        }

        val prefs = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
        // check if tutorial must be shown by settings of app

        if (prefs.getBoolean(Constants.PREFS_TUTORIAL, true)) {
            showTutorial()

            Log.d("Prefs", (prefs.getBoolean(Constants.PREFS_TUTORIAL, false)).toString())
            val editor = prefs.edit();
            editor.putBoolean(Constants.PREFS_TUTORIAL, false)
            editor.apply()
        }

        Log.d("Prefs", (prefs.getBoolean(Constants.PREFS_TUTORIAL, false)).toString())
        // if it not must be shown process
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

    override fun onResume() {
        super.onResume()
        mInterstitialAd.loadAd(AdRequest.Builder().addTestDevice("4AB458BDDA5C649998AB1AA81B0EEE8E").build())
    }

    // extras in intent, to check in settings, what activity must
    // be started after setting onDestroy
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_setting -> {
                startActivity(Intent(MainActivity@ this, SettingsActivity::class.java).putExtra("Activity", "Main"));
                finish()
                return true
            }
            R.id.action_credits -> {
                startActivity(Intent(MainActivity@ this, CreditsActivity::class.java).putExtra("Activity", "Main"))
                finish()
                return true
            }
            R.id.action_contact -> {
                showDialog()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun setDefaultPrefs() {
        val prefs = getSharedPreferences(com.blacksmithdreamapps.presentgo.Constants.SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = prefs.edit();
        editor.putString(com.blacksmithdreamapps.presentgo.Constants.CLOSE, "")
        editor.putString(com.blacksmithdreamapps.presentgo.Constants.OCCASION, "")
        editor.putString(com.blacksmithdreamapps.presentgo.Constants.BUDGET, "")
        editor.putString(com.blacksmithdreamapps.presentgo.Constants.AGE, "")
        editor.putString(com.blacksmithdreamapps.presentgo.Constants.CATEGORY, "")
        editor.putString(com.blacksmithdreamapps.presentgo.Constants.SEX, "")
        editor.apply()
    }

    private fun showTutorial() {
        // sequence example
        val config = ShowcaseConfig()
        config.delay = 500 // half second between each showcase view
        config.dismissTextColor = resources.getColor(R.color.colorGreenBackground) // green text
        config.fadeDuration = 700 // fade anim duration
        val sequence = MaterialShowcaseSequence(this)

        sequence.setConfig(config)

        sequence.addSequenceItem(previousButton,
                getString(R.string.button_back), getString(R.string.got_it_text))

        sequence.addSequenceItem(forwardButton,
                getString(R.string.button_skip), getString(R.string.got_it_text))

        sequence.addSequenceItem(main_tool_bar,
                getString(R.string.title_descr_etc), getString(R.string.got_it_text))

        sequence.start()
    }

    private fun showDialog() {
        AppRatingDialog.Builder()
                .setPositiveButtonText(getString(R.string.submit))
                .setNegativeButtonText(getString(R.string.cancel))
                .setNeutralButtonText(getString(R.string.later))
                .setNoteDescriptions(Arrays.asList(getString(R.string.very_bad), getString(R.string.bad), getString(R.string.normal), getString(R.string.very_good), getString(R.string.excelent)))
                .setDefaultRating(5)
                .setTitle(getString(R.string.rate_the_app))
                .setDescription(getString(R.string.rate_and_give))
                .setStarColor(R.color.colorAccent)
                .setTitleTextColor(R.color.colorAccent)
                .setDescriptionTextColor(R.color.colorBlueBackground)
                .setCommentTextColor(R.color.colorWhite)
                .setDefaultComment(getString(R.string.great_app))
                .setCommentBackgroundColor(R.color.colorBlueBackground)
                .create(this@MainActivity)
                .show()
    }


    override fun onPositiveButtonClicked(rate: Int, comment: String) {
        // interpret results, send it to analytics etc...

        if (rate > 3) {
            // open google play
            val appPackageName = packageName
            val marketIntent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName))
            marketIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET or Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(marketIntent)
        } else {
            // open email
            val intent = Intent(Intent.ACTION_SEND);
            intent.`package` = "com.google.android.gm";
            val strTo = arrayOf("blacksmithdreamapps@gmail.com")
            intent.putExtra(Intent.EXTRA_EMAIL, strTo);
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.application_feedback));
            if (rate > 1) {
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.i_left) + " " + rate + " " + getString(R.string.multiple_stars_rating) + " " + comment);

            } else {
                intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.i_left) + " " + rate + " " + getString(R.string.singe_star_rating) + " " + comment);
            }
            intent.type = "message/rfc822";
            startActivity(Intent.createChooser(intent, "Select Email Sending App :"));
        }
    }

    override fun onNegativeButtonClicked() {

    }

    override fun onNeutralButtonClicked() {

    }
}
