package com.blacksmithdreamapps.presentgo.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.blacksmithdreamapps.presentgo.Constants
import com.blacksmithdreamapps.presentgo.DataBaseHelper
import com.blacksmithdreamapps.presentgo.R
import com.blacksmithdreamapps.presentgo.SimpleGiftModel
import com.blacksmithdreamapps.presentgo.activities.settings.SettingsActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_gift.*
import kotlinx.android.synthetic.main.content_gift.*
import java.sql.SQLException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Developed and designed by Dream Apps Blacksmith
 * Code author is Boskin Kostya
 * On 007 7.03.2018
 */

class GiftActivity : AppCompatActivity() {

    lateinit var mAdView: AdView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gift)
        // removing title
        gift_tool_bar.title = ""
        // setting custom image instead of three dots
        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.setting_shape)
        gift_tool_bar.overflowIcon = drawable
        setSupportActionBar(gift_tool_bar)

        // enable back home button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);

        // admob
        MobileAds.initialize(this, "ca-app-pub-2631718830975455~9288409657")

        mAdView = findViewById(R.id.adViewGifts)
        // add here your device id, you can see it in logs
        val adRequest = AdRequest.Builder().addTestDevice("4AB458BDDA5C649998AB1AA81B0EEE8E").build()
        mAdView.loadAd(adRequest)

        // creating helper
        val myDbHelper = DataBaseHelper(this)
        // db creation
        val db = myDbHelper.handleDb()

        // hashMap to store all the prefs values
        val prefsHashMap = HashMap<String, String>()

        // writing values to hm
        for (entry in getSharedPreferences(Constants.SHARED_PREFS, MODE_PRIVATE).all.entries) {
            prefsHashMap.put(entry.key.toString(), entry.value.toString())
            Log.d("HashMapValues", entry.key + ": " + entry.value.toString())
        }
        // select query
        val table_name = "en"
        var query = "select name, description, image from $table_name where "

        // set of variables to trigger state, what the hell am i doing....
        var isFirstTime = true
        when {
            !prefsHashMap[Constants.CATEGORY].equals("") -> {
                when {
                    prefsHashMap[Constants.CATEGORY].equals(Constants.CATEGORY_FAMILY) -> {
                        if (isFirstTime) {
                            query += Constants.CATEGORY_FAMILY + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.CATEGORY_FAMILY + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.CATEGORY].equals(Constants.CATEGORY_FRIENDS) -> {
                        if (isFirstTime) {
                            query += Constants.CATEGORY_FRIENDS + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.CATEGORY_FRIENDS + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.CATEGORY].equals(Constants.CATEGORY_COLLEAGUES) -> {
                        if (isFirstTime) {
                            query += Constants.CATEGORY_COLLEAGUES + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.CATEGORY_COLLEAGUES + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.CATEGORY].equals(Constants.CATEGORY_BELOVED) -> {
                        if (isFirstTime) {
                            query += Constants.CATEGORY_BELOVED + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.CATEGORY_BELOVED + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.CATEGORY].equals(Constants.CATEGORY_SUPERIORS) -> {
                        if (isFirstTime) {
                            query += Constants.CATEGORY_SUPERIORS + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.CATEGORY_SUPERIORS + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.CATEGORY].equals(Constants.CATEGORY_HATED) -> {
                        if (isFirstTime) {
                            query += Constants.CATEGORY_HATED + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.CATEGORY_HATED + " " + "=" + "'" + "true" + "'"
                        }
                    }
                }
            }
            else -> {
                Log.d("inElse", "true")
                // random list of categories
                val list = arrayOf(Constants.CATEGORY_BELOVED, Constants.CATEGORY_COLLEAGUES, Constants.CATEGORY_FAMILY,
                        Constants.CATEGORY_FRIENDS, Constants.CATEGORY_HATED, Constants.CATEGORY_SUPERIORS)
                if (isFirstTime) {
                    query += chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                    isFirstTime = false
                } else {
                    query += " " + "and" + " " + chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                }
            }
        }
        when {
            !prefsHashMap[Constants.AGE].equals("") -> {
                when {
                    prefsHashMap[Constants.AGE].equals(Constants.AGE_CHILD) -> {
                        if (isFirstTime) {
                            query += Constants.AGE_CHILD + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.AGE_CHILD + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.AGE].equals(Constants.AGE_TEEN) -> {
                        if (isFirstTime) {
                            query += Constants.AGE_TEEN + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.AGE_TEEN + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.AGE].equals(Constants.AGE_ADULT) -> {
                        if (isFirstTime) {
                            query += Constants.AGE_ADULT + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.AGE_ADULT + " " + "=" + "'" + "true" + "'"
                        }
                    }
                }
            }
            else -> {
                // random list of ages
                val list = arrayOf(Constants.AGE_CHILD, Constants.AGE_TEEN,
                        Constants.AGE_ADULT, Constants.AGE_ELDERLY)
                if (isFirstTime) {
                    query += chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                    isFirstTime = false
                } else {
                    query += " " + "and" + " " + chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                }
            }
        }
        when {
            !prefsHashMap[Constants.BUDGET].equals("") -> {
                when {
                    prefsHashMap[Constants.BUDGET].equals(Constants.BUDGET_MINIMAL) -> {
                        if (isFirstTime) {
                            query += Constants.BUDGET_MINIMAL + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.BUDGET_MINIMAL + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.BUDGET].equals(Constants.BUDGET_LOW) -> {
                        if (isFirstTime) {
                            query += Constants.BUDGET_LOW + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.BUDGET_LOW + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.BUDGET].equals(Constants.BUDGET_MIDDLE) -> {
                        if (isFirstTime) {
                            query += Constants.BUDGET_MIDDLE + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.BUDGET_MIDDLE + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.BUDGET].equals(Constants.BUDGET_MIDDLE_PLUS) -> {
                        if (isFirstTime) {
                            query += Constants.BUDGET_MIDDLE_PLUS + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.BUDGET_MIDDLE_PLUS + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.BUDGET].equals(Constants.BUDGET_HIGH) -> {
                        if (isFirstTime) {
                            query += Constants.BUDGET_HIGH + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.BUDGET_HIGH + " " + "=" + "'" + "true" + "'"
                        }
                    }
                }
            }
            else -> {
                // random list of budgets
                val list = arrayOf(Constants.BUDGET_MINIMAL, Constants.BUDGET_LOW, Constants.BUDGET_MIDDLE,
                        Constants.BUDGET_MIDDLE_PLUS, Constants.BUDGET_HIGH)
                if (isFirstTime) {
                    query += chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                    isFirstTime = false
                } else {
                    query += " " + "and" + " " + chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                }
            }
        }
        when {
            !prefsHashMap[Constants.OCCASION].equals("") -> {
                when {
                    prefsHashMap[Constants.OCCASION].equals(Constants.OCCASION_BIRTHDAY) -> {
                        if (isFirstTime) {
                            query += Constants.OCCASION_BIRTHDAY + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.OCCASION_BIRTHDAY + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.OCCASION].equals(Constants.OCCASION_ANNIVERSARY) -> {
                        if (isFirstTime) {
                            query += Constants.OCCASION_ANNIVERSARY + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.OCCASION_ANNIVERSARY + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.OCCASION].equals(Constants.OCCASION_HOLIDAY) -> {
                        if (isFirstTime) {
                            query += Constants.OCCASION_HOLIDAY + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.OCCASION_HOLIDAY + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.OCCASION].equals(Constants.OCCASION_GRADUATION) -> {
                        if (isFirstTime) {
                            query += Constants.OCCASION_GRADUATION + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.OCCASION_GRADUATION + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.OCCASION].equals(Constants.OCCASION_JUST_BECAUSE) -> {
                        if (isFirstTime) {
                            query += Constants.OCCASION_JUST_BECAUSE + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.OCCASION_JUST_BECAUSE + " " + "=" + "'" + "true" + "'"
                        }
                    }
                }
            }
            else -> {
                // random list of categories
                val list = arrayOf(Constants.OCCASION_ANNIVERSARY, Constants.OCCASION_BIRTHDAY, Constants.OCCASION_GRADUATION,
                        Constants.OCCASION_HOLIDAY, Constants.OCCASION_JUST_BECAUSE, Constants.OCCASION_WEDDING)
                if (isFirstTime) {
                    query += chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                    isFirstTime = false
                } else {
                    query += " " + "and" + " " + chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                }
            }
        }
        when {
            !prefsHashMap[Constants.CLOSE].equals("") -> {
                when {
                    prefsHashMap[Constants.CLOSE].equals(Constants.CLOSE_STRANGER) -> {
                        if (isFirstTime) {
                            query += Constants.CLOSE_STRANGER + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.CLOSE_STRANGER + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.CLOSE].equals(Constants.CLOSE_ONE_TIME_SEEN) -> {
                        if (isFirstTime) {
                            query += Constants.CLOSE_ONE_TIME_SEEN + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.CLOSE_ONE_TIME_SEEN + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.CLOSE].equals(Constants.CLOSE_ACQUAINTANCE) -> {
                        if (isFirstTime) {
                            query += Constants.CLOSE_ACQUAINTANCE + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.CLOSE_ACQUAINTANCE + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.CLOSE].equals(Constants.CLOSE_COMRADE) -> {
                        if (isFirstTime) {
                            query += Constants.CLOSE_COMRADE + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.CLOSE_COMRADE + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.CLOSE].equals(Constants.CLOSE_CLOSE_PERSON) -> {
                        if (isFirstTime) {
                            query += Constants.CLOSE_CLOSE_PERSON + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.CLOSE_CLOSE_PERSON + " " + "=" + "'" + "true" + "'"
                        }
                    }


                }
            }
            else -> {
                // random list of close persons
                val list = arrayOf(Constants.CLOSE_STRANGER, Constants.CLOSE_ONE_TIME_SEEN,
                        Constants.CLOSE_ACQUAINTANCE, Constants.CLOSE_COMRADE, Constants.CLOSE_CLOSE_PERSON)
                if (isFirstTime) {
                    query += chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                    isFirstTime = false
                } else {
                    query += " " + "and" + " " + chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                }
            }
        }
        when {
            !prefsHashMap[Constants.SEX].equals("") -> {
                when {
                    prefsHashMap[Constants.SEX].equals(Constants.SEX_MALE) -> {
                        if (isFirstTime) {
                            query += Constants.SEX_MALE + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.SEX_MALE + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[Constants.SEX].equals(Constants.SEX_FEMALE) -> {
                        if (isFirstTime) {
                            query += Constants.SEX_FEMALE + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + Constants.SEX_FEMALE + " " + "=" + "'" + "true" + "'"
                        }
                    }
                }
            }
            else -> {
                // random list of person's sex
                val list = arrayOf(Constants.SEX_MALE, Constants.SEX_FEMALE)
                if (isFirstTime) {
                    query += chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                    isFirstTime = false
                } else {
                    query += " " + "and" + " " + chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                }
            }

        }

        val objectsArrayList = ArrayList<SimpleGiftModel>()

        // open database
        try {

            // raw for results
            val cursor: Cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    // add object to arrayList
                    objectsArrayList.add(SimpleGiftModel(cursor.getString(0), cursor.getString(1), cursor.getString(2)))
                    /*
                    Log.d("mLog", "ID = " + cursor.getString(0) +
                            ", Family = " + cursor.getString(1) +
                            ", Friends = " + cursor.getString(2) +
                            ", Colleagues = " + cursor.getString(3) +
                            ", Beloved = " + cursor.getString(4) +
                            ", Superiors = " + cursor.getString(5) +
                            ", PIH = " + cursor.getString(6) +
                            ", Male = " + cursor.getString(7) +
                            ", Female = " + cursor.getString(8) +
                            ", Child = " + cursor.getString(9) +
                            ", Teenager = " + cursor.getString(10) +
                            ", Adult = " + cursor.getString(11) +
                            ", Elderly = " + cursor.getString(12) +
                            ", BudgetMin = " + cursor.getString(13) +
                            ", BudgetLow = " + cursor.getString(14) +
                            ", BudgetMiddle = " + cursor.getString(15) +
                            ", BudgetMiddleUp =  " + cursor.getString(16) +
                            ", BudgetHigh =  " + cursor.getString(17) +
                            ", Birthday =  " + cursor.getString(18) +
                            ", Anniversary =  " + cursor.getString(19) +
                            ", Holiday =  " + cursor.getString(20) +
                            ", Graduation =  " + cursor.getString(21) +
                            ", Wedding =  " + cursor.getString(22) +
                            ", JustBeacause =  " + cursor.getString(23) +
                            ", OneTimeSeen =  " + cursor.getString(24) +
                            ", Stranger =  " + cursor.getString(25) +
                            ", Acquaintance =  " + cursor.getString(26) +
                            ", Comrade =  " + cursor.getString(27) +
                            ", Close =  " + cursor.getString(28) +
                            ", Name =  " + cursor.getString(29) +
                            ", Description =  " + cursor.getString(30) +
                            ", Image =  " + cursor.getString(31)
                    );*/

                } while (cursor.moveToNext())
            }
            Log.d("QUERYIS", query);
            Log.d("LISTIS", objectsArrayList.toString());
            cursor.close()
        } catch (sqle: SQLException) {
            sqle.printStackTrace()
            throw sqle
        }
        // getting only single gift here
        val randomItem = objectsArrayList[Random().nextInt(objectsArrayList.size)]

        // set title
        gift_title.text = getString(R.string.suggested_gift) + " " + randomItem.name.toUpperCase()

        // set description and parse string from database in one line
        val descriptionsArray = randomItem.description.split(",") as ArrayList<String>
        gift_description_0.text = "-" + " " + " " + descriptionsArray[0]
        gift_description_1.text = "-" + " " + descriptionsArray[1]
        gift_description_2.text = "-" + " " + descriptionsArray[2]

        // set image
        Picasso.get()
                .load(randomItem.imageLink)
                .fit()
                .centerCrop()
                .error(R.drawable.robot_msg_error)
                .into(gift_image)
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

    private fun chooseRandomly(objectsArrayList: Array<String>): String {
        return objectsArrayList[Random().nextInt(objectsArrayList.size)]
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_setting -> {
                startActivity(Intent(MainActivity@ this, SettingsActivity::class.java));
                return true
            }
            android.R.id.home -> {
                // close this activity and return to preview activity (if there is any)
                finish();
                return true
            }
        /*R.id.help -> {
            showHelp()
            return true
        }*/
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
