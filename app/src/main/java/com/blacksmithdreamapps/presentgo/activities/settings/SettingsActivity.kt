package com.blacksmithdreamapps.presentgo.activities.settings

import android.os.Bundle
import android.util.TypedValue
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.blacksmithdreamapps.presentgo.R
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

    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                // close this activity and return to preview activity (if there is any)
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
}
