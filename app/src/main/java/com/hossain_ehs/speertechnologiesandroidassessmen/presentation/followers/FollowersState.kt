package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.followers

import androidx.lifecycle.SavedStateHandle
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGitHubUser
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo

class FollowersState (
    private val savedStateHandle: SavedStateHandle

) {

    var userNameState = savedStateHandle.get<String>("username") ?: ""
        private set(value) {
            field = value
            savedStateHandle["username"] = value
        }


    var followersList = savedStateHandle.get<List<RemoteGithubUserInfo>>("followersList") ?: emptyList()
        private set(value) {
            field = value
            savedStateHandle["followersList"] = value
        }
    fun updateFollowersList(list: List<RemoteGithubUserInfo>) {
        this.followersList = list
    }

}