package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.following

import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo

sealed class FollowingEvents {
    data class OnUserClicked (val user : RemoteGithubUserInfo): FollowingEvents()

}