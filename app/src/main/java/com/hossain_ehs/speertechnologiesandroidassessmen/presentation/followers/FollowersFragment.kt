package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.followers

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.hossain_ehs.speertechnologiesandroidassessmen.R
import com.hossain_ehs.speertechnologiesandroidassessmen.data.util.FollowersUiEvents
import com.hossain_ehs.speertechnologiesandroidassessmen.databinding.FragmentFollowersBinding
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowersFragment : Fragment(R.layout.fragment_followers),
    FollowersAdapter.OnUserItemClickedListener
{

    private lateinit var binding: FragmentFollowersBinding
    private val viewModel: FollowersViewModel by viewModels()
    private lateinit var  followersAdapter : FollowersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFollowersBinding.bind(view)
        followersAdapter = FollowersAdapter(
            listener = this
        )
        subscribeToObservers()

    }

    private fun subscribeToObservers() {
        viewModel.followersChannel.asLiveData().observe(viewLifecycleOwner) { results ->
            when (results) {
                 is FollowersUiEvents.LoadData -> {
                     binding.apply {
                         rvFollowers.apply {
                             adapter = followersAdapter
                             addItemDecoration(
                                 DividerItemDecoration(
                                     view?.context,
                                     DividerItemDecoration.VERTICAL
                                 )
                             )
                             adapter = followersAdapter
                             layoutManager = LinearLayoutManager(requireContext())
                             followersAdapter.submitList(viewModel.state.followersList)
                         }

                     }
                }
                is FollowersUiEvents.ShowSnackBar -> {
                    Snackbar.make(
                        requireView(),
                        results.message.asString(requireContext()),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                is FollowersUiEvents.OnNavigateToUserInfoClicked -> {
                    val deeplink = NavDeepLinkRequest.Builder.fromUri(
                        Uri.parse(
                            getString(R.string.deep_link_uri
                            ).replace(
                                "{username}",
                                results.user.login
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
            FollowersEvents.OnUserClicked(user)
        )
    }
}