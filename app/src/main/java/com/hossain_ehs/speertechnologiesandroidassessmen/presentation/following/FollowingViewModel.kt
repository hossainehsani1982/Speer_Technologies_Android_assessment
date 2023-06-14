package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.following

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossain_ehs.speertechnologiesandroidassessmen.R
import com.hossain_ehs.speertechnologiesandroidassessmen.data.util.FollowersUiEvents
import com.hossain_ehs.speertechnologiesandroidassessmen.data.util.UiText
import com.hossain_ehs.speertechnologiesandroidassessmen.data.util.UserUiEvents
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FollowingViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel()
{
    var state = FollowingState(savedStateHandle)

    private val _followingChannel = Channel<FollowersUiEvents>()
    val followingChannel = _followingChannel.receiveAsFlow()

    init {
        getFollowings(state.userNameState)
    }

    fun onEvent(event: FollowingEvents) {
        when (event) {
            is FollowingEvents.OnUserClicked -> {
                viewModelScope.launch {
                    _followingChannel.send(
                        FollowersUiEvents.OnNavigateToUserInfoClicked(event.user))
                }
            }
        }
    }

    private fun getFollowings(userName: String) {

        viewModelScope.launch {
            useCases.getUserFollowings(
                userName
            ).onSuccess {
                state.updateFollowingList(it)
                _followingChannel.send(FollowersUiEvents.LoadData)

            }
                .onFailure {
                    _followingChannel.send(
                        FollowersUiEvents.ShowSnackBar(
                            UiText.ResourceString(R.string.error_no_following)
                        )
                    )
                }

        }
    }
}