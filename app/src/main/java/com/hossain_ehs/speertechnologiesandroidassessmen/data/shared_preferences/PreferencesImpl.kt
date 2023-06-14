package com.hossain_ehs.speertechnologiesandroidassessmen.data.shared_preferences

import android.content.SharedPreferences
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.shared_preferences.Preferences

class PreferencesImpl(
    private val sharedPreferences: SharedPreferences
) : Preferences {
    override fun saveToken(token: String) {
        TODO("Not yet implemented")
    }

    override fun getToken(): String {
        TODO("Not yet implemented")
    }
}