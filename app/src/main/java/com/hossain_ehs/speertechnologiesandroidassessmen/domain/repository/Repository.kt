package com.hossain_ehs.speertechnologiesandroidassessmen.domain.repository

import androidx.lifecycle.LiveData
import com.hossain_ehs.speertechnologiesandroidassessmen.data.local.entity.GitHubUserEntity
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGitHubUser
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo

interface Repository {

    suspend fun searchForUser(
        userName : String
    ): Result<RemoteGitHubUser>

    suspend fun getUserInfo(
        userName : String
    ): Result<RemoteGithubUserInfo>

    suspend fun getUserFollowers(
        userName: String
    ): Result<List<RemoteGithubUserInfo>>

    suspend fun getUserFollowings(
        userName: String
    ): Result<List<RemoteGithubUserInfo>>


    suspend fun insertGitHubUser(gitHubUserEntity: GitHubUserEntity)

    suspend fun deleteGitHubUser(gitHubUserEntity: GitHubUserEntity)

    fun getAllUsers(): LiveData<GitHubUserEntity>
}