package com.blacksmithdreamapps.presentgo.activities.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.SwitchPreference
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.blacksmithdreamapps.presentgo.Constants
import com.blacksmithdreamapps.presentgo.R
import com.blacksmithdreamapps.presentgo.activities.MainActivity
import kotlinx.android.synthetic.main.settings_toolbar.*




/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.02.2018.
 */

class SettingsActivity : AppCompatPreferenceActivity() {

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

        val constants = Constants()


        val language = findPreference("Languages") as ListPreference
        language.value = getSharedPreferences(constants.SHARED_PREFS, Context.MODE_PRIVATE).getString(constants.PREFS_LANGUAGE, "")

        val tutorial = findPreference("Tutorial") as SwitchPreference
        tutorial.isChecked = false
        // trick to set value to back
        // tutorial.isEnabled = !tutorial.isEnabled
        tutorial.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            if (newValue as Boolean == true) {
                setInPrefs(true, constants.PREFS_TUTORIAL, constants)
                setInPrefs(true, constants.PREFS_TUTORIAL_IS_SHOWN, constants)
                startMainActivity()
                true
            } else {
                setInPrefs(false, constants.PREFS_TUTORIAL, constants)
                setInPrefs(false, constants.PREFS_TUTORIAL_IS_SHOWN, constants)
                startMainActivity()
                true
            }
        }

        // setting animation showing in preferences
        val animation = findPreference("Animation") as SwitchPreference
        animation.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->

            if (newValue.toString() == "true") {
                setInPrefs(true, constants.PREFS_ANIMATION, constants)
                startMainActivity()
                true
            } else {
                setInPrefs(false, constants.PREFS_ANIMATION, constants)
                startMainActivity()
                true
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                // close this activity and return to preview activity (if there is any)
                startMainActivity()
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

    private fun setInPrefs(b: Boolean?, nameOfPrefs: String, constants: Constants) {
        val preferences = getSharedPreferences(constants.SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(nameOfPrefs, b!!)
        editor.apply()
    }
    private fun startMainActivity() {
        startActivity(Intent(this@SettingsActivity, MainActivity::class.java))
        finish()
    }
}
