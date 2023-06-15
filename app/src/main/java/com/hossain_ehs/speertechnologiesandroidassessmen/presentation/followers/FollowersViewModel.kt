package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.followers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.hossain_ehs.speertechnologiesandroidassessmen.R
import com.hossain_ehs.speertechnologiesandroidassessmen.data.util.FollowersUiEvents
import com.hossain_ehs.speertechnologiesandroidassessmen.data.util.UiText
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.usecase.UseCases
import com.hossain_ehs.speertechnologiesandroidassessmen.presentation.paging.AppPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
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

    val loading = MutableLiveData<Boolean>()

    val followersList = Pager(PagingConfig(1)) {
        AppPagingSource(useCases, state.userNameState)
    }.flow.cachedIn(viewModelScope)

    val mutableFollowersList = MutableLiveData<List<RemoteGithubUserInfo>>()

    private fun getFollowers(useName: String) {

        viewModelScope.launch {
            useCases.getUserFollowers(useName).let { response ->
                loading.postValue(false)
                if (response.isSuccessful) {
                    val followers = response.body()
                    //state.updateFollowersList(followers!!)
                    mutableFollowersList.postValue(followers!!)
                    loading.postValue(true)
                    _followersChannel.send(FollowersUiEvents.LoadData)
                } else {
                    _followersChannel.send(
                        FollowersUiEvents.ShowSnackBar(
                            UiText.ResourceString(R.string.error_no_following)
                        )
                    )
                }
            }
        }
    }


}