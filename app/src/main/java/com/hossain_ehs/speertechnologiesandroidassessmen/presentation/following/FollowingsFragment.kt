package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.following

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.NavDeepLinkRequest
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hossain_ehs.speertechnologiesandroidassessmen.R
import com.hossain_ehs.speertechnologiesandroidassessmen.data.util.FollowersUiEvents
import com.hossain_ehs.speertechnologiesandroidassessmen.databinding.FragmentFollowingsBinding
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowingsFragment : Fragment(R.layout.fragment_followings),
FollowingAdapter.OnUserItemClickedListener
{

    private lateinit var binding : FragmentFollowingsBinding
    private val viewModel : FollowingViewModel by viewModels()
    private lateinit var followingAdapter : FollowingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFollowingsBinding.bind(view)
        followingAdapter = FollowingAdapter(
            listener = this
        )
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.followingChannel.asLiveData().observe(viewLifecycleOwner) { result ->
            when (result) {
                FollowersUiEvents.LoadData -> {
                    binding.apply {
                        rvFollowings.apply {
                            adapter = followingAdapter
                            addItemDecoration(
                                DividerItemDecoration(
                                    view?.context,
                                    DividerItemDecoration.VERTICAL
                                )
                            )
                            adapter = followingAdapter
                            layoutManager = LinearLayoutManager(requireContext())
                            followingAdapter.submitList(viewModel.state.followingList)
                        }
                    }
                }
                is FollowersUiEvents.ShowSnackBar -> {
                    Snackbar.make(
                        requireView(),
                        result.message.asString(requireContext()),
                        Snackbar.LENGTH_LONG
                    ).show()
                }

                is FollowersUiEvents.OnNavigateToUserInfoClicked -> {
                    val deeplink = NavDeepLinkRequest.Builder.fromUri(
                        Uri.parse(
                            getString(R.string.deep_link_uri
                            ).replace(
                                "{username}",
                                result.user.login
                            )
                        )
                    ).build()
                    findNavController().navigate(deeplink)
                }
            }
        }
    }

    override fun onUserClicked(user: RemoteGithubUserInfo) {
        viewModel.onEvent(
            FollowingEvents.OnUserClicked(user)
        )
    }
}