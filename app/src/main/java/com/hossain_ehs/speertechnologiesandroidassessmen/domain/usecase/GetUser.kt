package com.hossain_ehs.speertechnologiesandroidassessmen.domain.usecase

import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGitHubUser
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.repository.Repository

class GetUser (
    private val repository: Repository
        )
{
    suspend operator fun invoke(
        userName: String
    ): Result<RemoteGitHubUser>{
        return repository.searchForUser(
            userName = userName.trim()
        )
    }

}