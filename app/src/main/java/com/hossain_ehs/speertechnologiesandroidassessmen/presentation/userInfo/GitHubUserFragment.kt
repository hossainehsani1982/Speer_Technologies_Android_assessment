package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.userInfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.hossain_ehs.speertechnologiesandroidassessmen.R
import com.hossain_ehs.speertechnologiesandroidassessmen.data.util.UserUiEvents
import com.hossain_ehs.speertechnologiesandroidassessmen.databinding.FragmentGitHubUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubUserFragment : Fragment(R.layout.fragment_git_hub_user) {

    private lateinit var binding: FragmentGitHubUserBinding
    private val viewModel: GitHubUsersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGitHubUserBinding.bind(view)
        subscribeToObservers()

        binding.apply {
            etSearchEditText.setText(viewModel.state.searchQuerState)
            etSearchEditText.doOnTextChanged { text, _, _, _ ->
                if (text!!.isNotEmpty() || text.isNotBlank() || text.trim() != "") {
                    viewModel.onEvent(
                        GitHubUserEvents
                            .OnSearchTextChange(text.toString())
                    )
                }
            }

            ibSearch.setOnClickListener {
                if (viewModel.state.searchQuerState != "") {
                    viewModel.onEvent(
                        GitHubUserEvents.OnUserSearchClicked
                    )
                }
            }

            tvFollowers.setOnClickListener {
                viewModel.onEvent(
                    GitHubUserEvents.OnFollowersClicked
                )
            }

            tvFollowing.setOnClickListener {
                viewModel.onEvent(
                    GitHubUserEvents.OnFollowingClicked
                )
            }

        }


    }

    private fun subscribeToObservers() {
        viewModel.userChannel.asLiveData().observe(viewLifecycleOwner) { events ->
            when (events) {
                is UserUiEvents.LoadData -> {
                    binding.apply {
                        Glide.with(requireContext())
                            .load(viewModel.state.avatarUrl)
                            .into(ivProfileImage)

                        val userNameText = getString(
                            R.string.username_text,
                            viewModel.state.userNameState)
                        tvUsername.text = userNameText

                        val nameText = getString(
                            R.string.name_text,
                            viewModel.state.nameState)
                        tvName.text = nameText

                        val flowerText = getString(
                            R.string.followers_text,
                            viewModel.state.followerCount)
                        tvFollowers.text = flowerText

                        val followingText = getString(
                            R.string.following_text,
                            viewModel.state.followingCount.toString())
                        tvFollowing.text = followingText

                    }
                }

                is UserUiEvents.OnNavigateToFollowerScreen -> {
                    val action = GitHubUserFragmentDirections
                        .actionGitHubUserFragmentToFollowersFragment(
                            username = events.username
                        )
                    findNavController().navigate(action)
                }

                is UserUiEvents.OnNavigateToFollowingScreen -> {
                    val action = GitHubUserFragmentDirections
                        .actionGitHubUserFragmentToFollowingsFragment(
                            username = events.username
                        )
                    findNavController().navigate(action)
                }

                is UserUiEvents.OnSearchButtonClicked -> {

                }

                is UserUiEvents.ShowSnackBar -> {
                    Snackbar.make(
                        requireView(),
                        events.message.asString(requireContext()),
                        Snackbar.LENGTH_LONG
                    ).show()
                }


            }
        }
    }

}