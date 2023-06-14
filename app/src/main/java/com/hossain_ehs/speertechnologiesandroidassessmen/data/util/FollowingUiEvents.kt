package com.hossain_ehs.speertechnologiesandroidassessmen.data.util

import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo

sealed class FollowingUiEvents {
    data class OnNavigateToUserInfoClicked(val user : RemoteGithubUserInfo): FollowingUiEvents()
    object LoadData : FollowingUiEvents()
    data class ShowSnackBar(val message: UiText) : FollowingUiEvents()


}