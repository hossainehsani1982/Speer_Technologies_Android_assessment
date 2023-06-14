package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.userInfo

import androidx.lifecycle.SavedStateHandle
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGitHubUser

class GitHubUsersState(
    private val savedStateHandle: SavedStateHandle
) {
    var searchQuerState = savedStateHandle.get<String>("searchTextState") ?: ""
        private set(value) {
            field = value
            savedStateHandle["searchTextState"] = value
        }
    fun updateSearchText(searchText: String) {
        this.searchQuerState = searchText
    }

    var userNameState = savedStateHandle.get<String>("userNameState") ?: ""
        private set(value) {
            field = value
            savedStateHandle["userNameState"] = value
        }

    fun updateUserName(userName: String) {
        this.userNameState = userName
    }

    var nameState = savedStateHandle.get<String>("nameState") ?: ""
        private set(value) {
            field = value
            savedStateHandle["userNameState"] = value
        }

    fun updateName(name: String) {
        this.nameState = name
    }

    var followerCount = savedStateHandle.get<Int>("followerCount") ?: ""
        private set(value) {
            field = value
            savedStateHandle["followerCount"] = value
        }

    var avatarUrl = savedStateHandle.get<String>("avatarUrl") ?: ""
        private set(value) {
            field = value
            savedStateHandle["avatarUrl"] = value
        }
    fun updateAvatarUrl(avatarUrl: String) {
        this.avatarUrl = avatarUrl
    }

   var followersCount = savedStateHandle.get<Int>("followersCount") ?: 0
        private set(value) {
            field = value
            savedStateHandle["followersCount"] = value
        }

    fun updateFollowerCount(count: Int) {
        this.followerCount = count
    }

    var followingCount = savedStateHandle.get<Int>("followingCount") ?: 0
        private set(value) {
            field = value
            savedStateHandle["followingCount"] = value
        }
    fun updateFollowingCount(count: Int) {
        this.followingCount = count
    }



}