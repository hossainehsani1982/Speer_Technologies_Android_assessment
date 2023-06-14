package com.hossain_ehs.speertechnologiesandroidassessmen.domain.shared_preferences

import com.hossain_ehs.speertechnologiesandroidassessmen.data.local.entity.GitHubUserEntity

interface Preferences {

    fun saveToken(token: String)

    fun getToken(): String

    companion object{
        const val KEY_TOKEN = "token"
    }
}