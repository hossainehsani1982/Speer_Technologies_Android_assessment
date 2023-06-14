package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.followers

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossain_ehs.speertechnologiesandroidassessmen.R
import com.hossain_ehs.speertechnologiesandroidassessmen.data.util.FollowersUiEvents
import com.hossain_ehs.speertechnologiesandroidassessmen.data.util.UiText
import com.hossain_ehs.speertechnologiesandroidassessmen.data.util.UserUiEvents
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FollowersViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state = FollowersState(savedStateHandle)

    private val _followersChannel = Channel<FollowersUiEvents>()
    val followersChannel = _followersChannel.receiveAsFlow()

    init {
        getFollowers(state.userNameState)
    }

    fun onEvent(event: FollowersEvents) {
        when (event) {
            is FollowersEvents.OnUserClicked -> {
                viewModelScope.launch {
                    _followersChannel.send(FollowersUiEvents.OnNavigateToUserInfoClicked(event.user))
                }
            }
        }
    }


    private fun getFollowers(useName: String) {

        viewModelScope.launch {
            useCases.getUserFollowers(
                useName
            ).onSuccess {

                state.updateFollowersList(it)
                _followersChannel.send(FollowersUiEvents.LoadData)

            }.onFailure {
                _followersChannel.send(
                    FollowersUiEvents.ShowSnackBar(
                        UiText.ResourceString(
                            R.string.error_no_following
                        )
                    )
                )
            }

        }
    }
}