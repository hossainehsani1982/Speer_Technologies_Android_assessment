package com.hossain_ehs.speertechnologiesandroidassessmen.data.util

import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo

sealed class FollowersUiEvents{
    data class OnNavigateToUserInfoClicked(val user : RemoteGithubUserInfo): FollowersUiEvents()
    object LoadData : FollowersUiEvents()
    data class ShowSnackBar(val message: UiText) : FollowersUiEvents()

}
