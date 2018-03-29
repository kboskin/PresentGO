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

    private fun setLocalization(langId: String, constants: Constants) {
        // set localization into shared prefs
        val preferences = getSharedPreferences(constants.SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = preferences.edit();
        editor.putString(constants.PREFS_LANGUAGE, langId)
        editor.putBoolean(constants.PREFS_LANGUAGE_WAS_SET, true)
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
            val constants = Constants()
            when {
                p0.id == en_lang_image_view.id -> {
                    Log.d("LangTag", "En")
                    setLocalization("en", constants)
                    startMainActivity()
                }
                p0.id == ru_lang_image_view.id -> {
                    Log.d("LangTag", "RU")
                    setLocalization("ru", constants)
                    startMainActivity()
                }
                else -> {

                }
            }
        }
    }

}
