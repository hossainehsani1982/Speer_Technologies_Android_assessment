package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.followers

import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo

sealed class FollowersEvents {
    data class OnUserClicked (val user : RemoteGithubUserInfo): FollowersEvents()
}