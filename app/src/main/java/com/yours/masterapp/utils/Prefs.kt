package com.yours.masterapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


class Prefs(context: Context) {
    private val CURRENT_LANGUAGE_KEY: String = "prefs.current_language"
    val PREFS_FILENAME = "com.mina.masterapp"
    val LOGGED_IN_USER = "LOGGED_IN_USER"
    val USER_PREF = "USER_PREF"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);
    val gson = Gson()

    //my prefs
    var isLogedIn: Boolean
        get() = prefs.getBoolean(LOGGED_IN_USER, false)
        set(value) = prefs.edit().putBoolean(LOGGED_IN_USER, value).apply()

}