package com.hossain_ehs.speertechnologiesandroidassessmen.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.hossain_ehs.speertechnologiesandroidassessmen.R
import com.hossain_ehs.speertechnologiesandroidassessmen.databinding.FragmentUserInfoBinding
import com.hossain_ehs.speertechnologiesandroidassessmen.presentation.userInfo.GitHubUsersViewModel


class UserInfoFragment : Fragment(R.layout.fragment_user_info) {
    private lateinit var binding: FragmentUserInfoBinding
    private val viewModel: GitHubUsersViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserInfoBinding.bind(view)



    }
}