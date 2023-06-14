package com.hossain_ehs.speertechnologiesandroidassessmen.data.util

/*
* this class is used to handle all the events that are triggered from the UI
*/

sealed class UserUiEvents {
    data class OnSearchButtonClicked(val username: String) : UserUiEvents()
    data class OnNavigateToFollowerScreen(val username: String) : UserUiEvents()
    data class OnNavigateToFollowingScreen(val username: String) : UserUiEvents()
    data class ShowSnackBar(val message: UiText) : UserUiEvents()
    object LoadData : UserUiEvents()
}