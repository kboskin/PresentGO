package com.blacksmithdreamapps.presentgo.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.blacksmithdreamapps.presentgo.R
import kotlinx.android.synthetic.main.activity_credits.*

/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 027 27.03.2018
 */
class CreditsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)

        // no title
        credits_tool_bar.title = getString(R.string.licence)
        setSupportActionBar(credits_tool_bar)

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
            else -> super.onOptionsItemSelected(item)
        }
    }
}
