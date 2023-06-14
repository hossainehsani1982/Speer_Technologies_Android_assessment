package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.userInfo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hossain_ehs.speertechnologiesandroidassessmen.R
import com.hossain_ehs.speertechnologiesandroidassessmen.data.util.UserUiEvents
import com.hossain_ehs.speertechnologiesandroidassessmen.data.util.UiText
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitHubUsersViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val userName = savedStateHandle.get<String>("username")
    val state = GitHubUsersState(savedStateHandle)

    init {
        if (userName != null) {
            state.updateSearchText(userName)
            executeSearch()
        }
    }

    private val _userChannel = Channel<UserUiEvents>()
    val userChannel = _userChannel.receiveAsFlow()

    fun onEvent(event: GitHubUserEvents) {
        when (event) {
            is GitHubUserEvents.OnUserSearchClicked -> {
                executeSearch()
            }

            is GitHubUserEvents.OnSearchTextChange -> {
                state.updateSearchText(event.searchQuery)
            }

            is GitHubUserEvents.OnFollowersClicked -> {
                viewModelScope.launch {
                    _userChannel.send(UserUiEvents.OnNavigateToFollowerScreen(state.userNameState))
                }
            }

            GitHubUserEvents.OnFollowingClicked -> {
                viewModelScope.launch {
                    _userChannel.send(UserUiEvents.OnNavigateToFollowingScreen(state.userNameState))
                }
            }
        }
    }


    private fun executeSearch() {
        viewModelScope.launch {
            useCases.getUserInfo(
                state.searchQuerState
            ).onSuccess {

                state.updateUserName(it.login)
                state.updateName(it.name)
                state.updateAvatarUrl(it.avatar_url)
                state.updateFollowingCount(it.following)
                state.updateFollowerCount(it.followers)
                _userChannel.send(UserUiEvents.LoadData)
                _userChannel.send(UserUiEvents.ShowSnackBar(UiText.ResourceString(R.string.success_search)))
            }.onFailure {
                _userChannel.send(UserUiEvents.ShowSnackBar(UiText.ResourceString(R.string.error_getting_user)))
            }
        }
    }






}
