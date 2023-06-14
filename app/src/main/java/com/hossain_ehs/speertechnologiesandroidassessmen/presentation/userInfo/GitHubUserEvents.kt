package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.userInfo

sealed class GitHubUserEvents {
    object OnUserSearchClicked: GitHubUserEvents()
    data class OnSearchTextChange(val searchQuery: String) : GitHubUserEvents()
    object OnFollowersClicked : GitHubUserEvents()
    object OnFollowingClicked : GitHubUserEvents()
}