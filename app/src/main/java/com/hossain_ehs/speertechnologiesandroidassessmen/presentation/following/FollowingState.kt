package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.following

import androidx.lifecycle.SavedStateHandle
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGitHubUser
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo

class FollowingState (
    private val savedStateHandle: SavedStateHandle
) {

    var userNameState = savedStateHandle.get<String>("username") ?: ""
    private set(value) {
        field = value
        savedStateHandle["username"] = value
    }

    var followingList = savedStateHandle.get<List<RemoteGithubUserInfo>>("followingList") ?: emptyList()
        private set(value) {
            field = value
            savedStateHandle["followingList"] = value
        }
    fun updateFollowingList(list: List<RemoteGithubUserInfo>) {
        this.followingList = list
    }

}