package com.blacksmithdreamapps.presentgo.activities.settings

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.Preference.OnPreferenceChangeListener
import android.preference.SwitchPreference
import android.util.Log
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.blacksmithdreamapps.presentgo.Constants
import com.blacksmithdreamapps.presentgo.R
import com.blacksmithdreamapps.presentgo.activities.GiftActivity
import com.blacksmithdreamapps.presentgo.activities.MainActivity
import kotlinx.android.synthetic.main.settings_toolbar.*
import java.util.*


/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.02.2018.
 */

class SettingsActivity : AppCompatPreferenceActivity() {
    lateinit var language: ListPreference
    lateinit var starterIntent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings_screen)
        //fragmentManager.beginTransaction().replace(android.R.id.content, MyPreferenceFragment()).commit()
        // lol:)
        val horizontalMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                2f, resources.displayMetrics).toInt()

        val verticalMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                2f, resources.displayMetrics).toInt()

        val topMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (resources.getDimension(R.dimen.activity_vertical_margin).toInt() + 30).toFloat(),
                resources.displayMetrics).toInt()

        listView.setPadding(horizontalMargin, topMargin, horizontalMargin, verticalMargin)
        // set back button
        layoutInflater.inflate(R.layout.settings_toolbar, findViewById<View>(android.R.id.content) as ViewGroup)
        val toolbar = toolbar
        setSupportActionBar(toolbar)

        // enable back home button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);


        starterIntent = intent

        language = findPreference("Languages") as ListPreference
        language.value = setDefaultValue()

        language.onPreferenceChangeListener = OnPreferenceChangeListener { preference, newValue ->
            val index = language.findIndexOfValue(newValue.toString())

            if (index != -1) {
                if (index == 0) {
                    setLocalization("ru")
                } else if (index == 1) {
                    setLocalization("en")
                } else {
                    Log.d("Some shit", "oh dude")
                }
            }
            true
        };

        val tutorial = findPreference("Tutorial") as SwitchPreference
        tutorial.isChecked = false
        // trick to set value to back
        // tutorial.isEnabled = !tutorial.isEnabled
        tutorial.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            if (newValue as Boolean == true) {
                setInPrefs(true, Constants.PREFS_TUTORIAL)
                setInPrefs(true, Constants.PREFS_TUTORIAL_IS_SHOWN)
                true
            } else {
                setInPrefs(false, Constants.PREFS_TUTORIAL)
                setInPrefs(false, Constants.PREFS_TUTORIAL_IS_SHOWN)
                true
            }
        }

        // setting animation showing in preferences
        val animation = findPreference("Animation") as SwitchPreference
        animation.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->

            if (newValue.toString() == "true") {
                setInPrefs(true, Constants.PREFS_ANIMATION)
                true
            } else {
                setInPrefs(false, Constants.PREFS_ANIMATION)
                true
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                // close this activity and return to preview activity (if there is any)
                startParentActivity()
                finish();
                true
            }
        /*R.id.help -> {
            showHelp()
            return true
        }*/
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setInPrefs(b: Boolean?, nameOfPrefs: String) {
        val preferences = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(nameOfPrefs, b!!)
        editor.apply()
    }

    // method to restart parent activity
    // takes value from starter intent
    // checks for value and starts activity
    private fun startParentActivity() {
        when {
            getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE).getString(Constants.ACTIVITY, "Main") == "Main" -> {
                startActivity(Intent(this@SettingsActivity, MainActivity::class.java))
                finish()
            }
            getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE).getString(Constants.ACTIVITY, "Main") == "Gift" -> {
                startActivity(Intent(this@SettingsActivity, GiftActivity::class.java))
                finish()
            }
            else -> {

            }
        }

    }

    private fun setLocalization(langId: String) {
        // set localization into shared prefs
        val preferences = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = preferences.edit();
        editor.putString(Constants.PREFS_LANGUAGE, langId)
        editor.putBoolean(Constants.PREFS_LANGUAGE_WAS_SET, true)
        editor.apply()

        val locale = Locale(langId)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config,
                baseContext.resources.displayMetrics)
        startActivity(Intent(SettingsActivity@ this, SettingsActivity::class.java))
        finish()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        applicationContext.resources.updateConfiguration(newConfig, baseContext.resources.displayMetrics)
        preferenceScreen = null
        addPreferencesFromResource(R.xml.settings_screen);
        setTitle(R.string.app_name);
        language.value = setDefaultValue()


        // Checks the active language
        if (newConfig.locale === Locale.ENGLISH) {
            Toast.makeText(this, "English", Toast.LENGTH_SHORT).show()
        } else if (newConfig.locale === Locale.FRENCH) {
            Toast.makeText(this, "French", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setDefaultValue(): String {
        val lng = getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE).getString(Constants.PREFS_LANGUAGE, "")
        return if (lng == "ru") {
            "Русский"
        } else {
            "English"
        }
    }

    override fun onBackPressed() {
        startParentActivity()
    }
}
