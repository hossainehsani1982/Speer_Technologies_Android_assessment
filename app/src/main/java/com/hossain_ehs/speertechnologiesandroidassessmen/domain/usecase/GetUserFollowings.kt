package com.hossain_ehs.speertechnologiesandroidassessmen.domain.usecase

import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGitHubUser
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.repository.Repository

class GetUserFollowings (
    private val repository: Repository
        ) {

    suspend operator fun invoke(
        userName: String
    ) : Result<List<RemoteGithubUserInfo>> {
        return repository.getUserFollowings(
            userName = userName
        )
    }
}