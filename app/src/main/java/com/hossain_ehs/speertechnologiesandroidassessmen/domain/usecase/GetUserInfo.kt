package com.hossain_ehs.speertechnologiesandroidassessmen.domain.usecase

import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.repository.Repository

class GetUserInfo (
    private val repository: Repository
        ){
    suspend operator fun invoke(
        userName: String
    ) : Result<RemoteGithubUserInfo> {
        return repository.getUserInfo(
            userName = userName
        )
    }
}