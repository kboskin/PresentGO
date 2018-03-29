package com.blacksmithdreamapps.presentgo.activities

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.blacksmithdreamapps.presentgo.Constants
import com.blacksmithdreamapps.presentgo.R
import kotlinx.android.synthetic.main.activity_change_lang.*
import java.util.*

class ChooseLangActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_lang)

        ru_lang_image_view.setOnClickListener(this)
        en_lang_image_view.setOnClickListener(this)
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
    }

    private fun startMainActivity() {
        startActivity(Intent(this@ChooseLangActivity, MainActivity::class.java))
        finish()
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when {
                p0.id == en_lang_image_view.id -> {
                    Log.d("LangTag", "En")
                    setLocalization("en")
                    startMainActivity()
                }
                p0.id == ru_lang_image_view.id -> {
                    Log.d("LangTag", "RU")
                    setLocalization("ru")
                    startMainActivity()
                }
                else -> {

                }
            }
        }
    }

}
