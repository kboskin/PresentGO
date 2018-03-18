package com.kosboss.gogift

import android.annotation.SuppressLint
import android.database.Cursor
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_gift.*
import kotlinx.android.synthetic.main.content_gift.*
import java.sql.SQLException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class GiftActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gift)
        // removing title
        gift_tool_bar.title = ""
        setSupportActionBar(gift_tool_bar)

        // creating helper
        val myDbHelper = DataBaseHelper(this)
        // db creation
        val db = myDbHelper.handleDb()


        val constants = Constants()
        // hashMap to store all the prefs values
        val prefsHashMap = HashMap<String, String>()

        // writing values to hm
        for (entry in getSharedPreferences(constants.SHARED_PREFS, MODE_PRIVATE).all.entries) {
            prefsHashMap.put(entry.key.toString(), entry.value.toString())
            Log.d("HashMapValues", entry.key + ": " + entry.value.toString())
        }
        // select query
        val table_name = "en"
        var query = "select name, description, image from $table_name where "

        // set of variables to trigger state, what the hell am i doing....
        var isFirstTime = true
        when {
            !prefsHashMap[constants.CATEGORY].equals("") -> {
                when {
                    prefsHashMap[constants.CATEGORY].equals(constants.CATEGORY_FAMILY) -> {
                        if (isFirstTime) {
                            query += constants.CATEGORY_FAMILY + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.CATEGORY_FAMILY + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.CATEGORY].equals(constants.CATEGORY_FRIENDS) -> {
                        if (isFirstTime) {
                            query += constants.CATEGORY_FRIENDS + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.CATEGORY_FRIENDS + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.CATEGORY].equals(constants.CATEGORY_COLLEAGUES) -> {
                        if (isFirstTime) {
                            query += constants.CATEGORY_COLLEAGUES + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.CATEGORY_COLLEAGUES + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.CATEGORY].equals(constants.CATEGORY_BELOVED) -> {
                        if (isFirstTime) {
                            query += constants.CATEGORY_BELOVED + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.CATEGORY_BELOVED + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.CATEGORY].equals(constants.CATEGORY_SUPERIORS) -> {
                        if (isFirstTime) {
                            query += constants.CATEGORY_SUPERIORS + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.CATEGORY_SUPERIORS + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.CATEGORY].equals(constants.CATEGORY_HATED) -> {
                        if (isFirstTime) {
                            query += constants.CATEGORY_HATED + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.CATEGORY_HATED + " " + "=" + "'" + "true" + "'"
                        }
                    }
                }
            }
            else -> {
                Log.d("inElse", "true")
                // random list of categories
                val list = arrayOf(constants.CATEGORY_BELOVED, constants.CATEGORY_COLLEAGUES, constants.CATEGORY_FAMILY,
                        constants.CATEGORY_FRIENDS, constants.CATEGORY_HATED, constants.CATEGORY_SUPERIORS)
                if (isFirstTime) {
                    query += chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                    isFirstTime = false
                } else {
                    query += " " + "and" + " " + chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                }
            }
        }
        when {
            !prefsHashMap[constants.AGE].equals("") -> {
                when {
                    prefsHashMap[constants.AGE].equals(constants.AGE_CHILD) -> {
                        if (isFirstTime) {
                            query += constants.AGE_CHILD + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.AGE_CHILD + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.AGE].equals(constants.AGE_TEEN) -> {
                        if (isFirstTime) {
                            query += constants.AGE_TEEN + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.AGE_TEEN + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.AGE].equals(constants.AGE_ADULT) -> {
                        if (isFirstTime) {
                            query += constants.AGE_ADULT + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.AGE_ADULT + " " + "=" + "'" + "true" + "'"
                        }
                    }
                }
            }
            else -> {
                // random list of ages
                val list = arrayOf(constants.AGE_CHILD, constants.AGE_TEEN,
                        constants.AGE_ADULT, constants.AGE_ELDERLY)
                if (isFirstTime) {
                    query += chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                    isFirstTime = false
                } else {
                    query += " " + "and" + " " + chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                }
            }
        }
        when {
            !prefsHashMap[constants.BUDGET].equals("") -> {
                when {
                    prefsHashMap[constants.BUDGET].equals(constants.BUDGET_MINIMAL) -> {
                        if (isFirstTime) {
                            query += constants.BUDGET_MINIMAL + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.BUDGET_MINIMAL + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.BUDGET].equals(constants.BUDGET_LOW) -> {
                        if (isFirstTime) {
                            query += constants.BUDGET_LOW + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.BUDGET_LOW + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.BUDGET].equals(constants.BUDGET_MIDDLE) -> {
                        if (isFirstTime) {
                            query += constants.BUDGET_MIDDLE + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.BUDGET_MIDDLE + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.BUDGET].equals(constants.BUDGET_MIDDLE_PLUS) -> {
                        if (isFirstTime) {
                            query += constants.BUDGET_MIDDLE_PLUS + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.BUDGET_MIDDLE_PLUS + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.BUDGET].equals(constants.BUDGET_HIGH) -> {
                        if (isFirstTime) {
                            query += constants.BUDGET_HIGH + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.BUDGET_HIGH + " " + "=" + "'" + "true" + "'"
                        }
                    }
                }
            }
            else -> {
            // random list of budgets
            val list = arrayOf(constants.BUDGET_MINIMAL, constants.BUDGET_LOW, constants.BUDGET_MIDDLE,
                    constants.BUDGET_MIDDLE_PLUS, constants.BUDGET_HIGH)
            if (isFirstTime) {
                query += chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                isFirstTime = false
            } else {
                query += " " + "and" + " " + chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
            }
        }
        }
        when {
            !prefsHashMap[constants.OCCASION].equals("") -> {
                when {
                    prefsHashMap[constants.OCCASION].equals(constants.OCCASION_BIRTHDAY) -> {
                        if (isFirstTime) {
                            query += constants.OCCASION_BIRTHDAY + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.OCCASION_BIRTHDAY + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.OCCASION].equals(constants.OCCASION_ANNIVERSARY) -> {
                        if (isFirstTime) {
                            query += constants.OCCASION_ANNIVERSARY + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.OCCASION_ANNIVERSARY + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.OCCASION].equals(constants.OCCASION_HOLIDAY) -> {
                        if (isFirstTime) {
                            query += constants.OCCASION_HOLIDAY + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.OCCASION_HOLIDAY + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.OCCASION].equals(constants.OCCASION_GRADUATION) -> {
                        if (isFirstTime) {
                            query += constants.OCCASION_GRADUATION + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.OCCASION_GRADUATION + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.OCCASION].equals(constants.OCCASION_JUST_BECAUSE) -> {
                        if (isFirstTime) {
                            query += constants.OCCASION_JUST_BECAUSE + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.OCCASION_JUST_BECAUSE + " " + "=" + "'" + "true" + "'"
                        }
                    }
                }
            }
            else -> {
                // random list of categories
                val list = arrayOf(constants.OCCASION_ANNIVERSARY, constants.OCCASION_BIRTHDAY, constants.OCCASION_GRADUATION,
                        constants.OCCASION_HOLIDAY, constants.OCCASION_JUST_BECAUSE, constants.OCCASION_WEDDING)
                if (isFirstTime) {
                    query += chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                    isFirstTime = false
                } else {
                    query += " " + "and" + " " + chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                }
            }
        }
        when {
            !prefsHashMap[constants.CLOSE].equals("") -> {
                when {
                    prefsHashMap[constants.CLOSE].equals(constants.CLOSE_STRANGER) -> {
                        if (isFirstTime) {
                            query += constants.CLOSE_STRANGER + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.CLOSE_STRANGER + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.CLOSE].equals(constants.CLOSE_ONE_TIME_SEEN) -> {
                        if (isFirstTime) {
                            query += constants.CLOSE_ONE_TIME_SEEN + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.CLOSE_ONE_TIME_SEEN + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.CLOSE].equals(constants.CLOSE_ACQUAINTANCE) -> {
                        if (isFirstTime) {
                            query += constants.CLOSE_ACQUAINTANCE + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.CLOSE_ACQUAINTANCE + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.CLOSE].equals(constants.CLOSE_COMRADE) -> {
                        if (isFirstTime) {
                            query += constants.CLOSE_COMRADE + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.CLOSE_COMRADE + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.CLOSE].equals(constants.CLOSE_CLOSE_PERSON) -> {
                        if (isFirstTime) {
                            query += constants.CLOSE_CLOSE_PERSON + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.CLOSE_CLOSE_PERSON + " " + "=" + "'" + "true" + "'"
                        }
                    }


                }
            }
            else -> {
                // random list of close persons
                val list = arrayOf(constants.CLOSE_STRANGER, constants.CLOSE_ONE_TIME_SEEN,
                        constants.CLOSE_ACQUAINTANCE, constants.CLOSE_COMRADE, constants.CLOSE_CLOSE_PERSON)
                if (isFirstTime) {
                    query += chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                    isFirstTime = false
                } else {
                    query += " " + "and" + " " + chooseRandomly(list) + " " + "=" + "'" + "true" + "'"
                }
            }
        }
        when {
            !prefsHashMap[constants.SEX].equals("") -> {
                when {
                    prefsHashMap[constants.SEX].equals(constants.SEX_MALE) -> {
                        if (isFirstTime) {
                            query += constants.SEX_MALE + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.SEX_MALE + " " + "=" + "'" + "true" + "'"
                        }
                    }
                    prefsHashMap[constants.SEX].equals(constants.SEX_FEMALE) -> {
                        if (isFirstTime) {
                            query += constants.SEX_FEMALE + " " + "=" + "'" + "true" + "'"
                            isFirstTime = false
                        } else {
                            query += " " + "and" + " " + constants.SEX_FEMALE + " " + "=" + "'" + "true" + "'"
                        }
                    }
                }
            }
            else -> {
                // random list of person's sex
                val list = arrayOf(constants.SEX_MALE, constants.SEX_FEMALE)
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
        gift_title.text = "Suggested gift is " + randomItem.name.toUpperCase()

        // set description and parse string from database in one line
        val descriptionsArray = randomItem.description.split(",") as ArrayList<String>
        gift_description_0.text = "-" + " " + descriptionsArray[0]
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
}
