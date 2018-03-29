package com.blacksmithdreamapps.presentgo.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.blacksmithdreamapps.presentgo.Constants
import java.util.*

class SplashActivity : AppCompatActivity() {

    var startIntent: Intent? = null
    private var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // lines makes activity to become full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val constants = Constants()
        if (isLangSet(constants)) {

            when {
                prefs!!.getString(constants.PREFS_LANGUAGE, "") == "en" -> {
                    val locale = Locale("en")
                    Locale.setDefault(locale)
                    val config = Configuration()
                    config.locale = locale
                    baseContext.resources.updateConfiguration(config,
                            baseContext.resources.displayMetrics)

                    Log.d("isHere", "inEN")

                }
                prefs!!.getString(constants.PREFS_LANGUAGE, "") == "ru" -> {
                    // Create a new Locale object
                    val locale = Locale("ru")
                    Locale.setDefault(locale)
                    // Create a new configuration object
                    val config = Configuration()
                    // Set the locale of the new configuration
                    config.locale = locale
                    // Update the configuration of the Application context
                    resources.updateConfiguration(
                            config,
                            resources.displayMetrics
                    )
                    Log.d("isHere", "inRU")
                }
                else -> Log.d("isHere", "inElse")
            }
            startIntent = Intent(this, MainActivity::class.java)
            startActivity(startIntent)
            finish()
        } else {
            startIntent = Intent(this, ChooseLangActivity::class.java)
            startActivity(startIntent)
            finish()
        }

    }


    private fun isLangSet(constants: Constants): Boolean {
        prefs = getSharedPreferences(constants.SHARED_PREFS, Context.MODE_PRIVATE)
        Log.d("Lang is", Locale.getDefault().language)
        return prefs!!.getBoolean(constants.PREFS_LANGUAGE_WAS_SET, false)
    }
}
