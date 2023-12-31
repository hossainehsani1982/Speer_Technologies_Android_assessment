package com.hossain_ehs.speertechnologiesandroidassessmen.domain.usecase

import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGitHubUser
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.repository.Repository
import retrofit2.Response

class GetUserFollowers(
    private val repository: Repository
) {
    suspend operator fun invoke(
        userName: String
    ) : Response<List<RemoteGithubUserInfo>> {
        return repository.getUserFollowers(
            userName = userName
        )
    }
}