package com.hossain_ehs.speertechnologiesandroidassessmen.data.repository

import androidx.lifecycle.LiveData
import com.hossain_ehs.speertechnologiesandroidassessmen.data.local.AppDao
import com.hossain_ehs.speertechnologiesandroidassessmen.data.local.entity.GitHubUserEntity
import com.hossain_ehs.speertechnologiesandroidassessmen.data.mapper.toRemoteGitHubUser
import com.hossain_ehs.speertechnologiesandroidassessmen.data.mapper.toRemoteUserInfo
import com.hossain_ehs.speertechnologiesandroidassessmen.data.remote.GitHubApi
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGitHubUser
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.repository.Repository
import retrofit2.Response

class RepositoryImpl(
    private val gitHubApi: GitHubApi,
    private val appDao: AppDao,
) : Repository {

    override suspend fun searchForUser(
        userName: String
    ): Result<RemoteGitHubUser> {
        return try {
            val response = gitHubApi.getUser(
                userName = userName,
            )
            Result.success(response.items.map {
                it.toRemoteGitHubUser()
            }.first())
        }catch (e: Exception) {

            Result.failure(e)
        }
    }

    override suspend fun getUserInfo(
        userName: String
    ): Result<RemoteGithubUserInfo> {
        return try {
            val response = gitHubApi.getUserInfo(
                userName = userName,
            )
            Result.success(response.toRemoteUserInfo())
        }catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun getUserFollowers(
        userName: String
    ): Response<List<RemoteGithubUserInfo>> {
        val response = gitHubApi.getUserFollowers(
            userName = userName,
        )
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Response.success(body.map {
                    it.toRemoteUserInfo()
                })
            }
        }
        return Response.error(response.code(), response.errorBody()!!)
    }



    override suspend fun getUserFollowings(
        userName: String
    ):  Result<List<RemoteGithubUserInfo>> {
        return try {
            val response = gitHubApi.getUserFollowings(
                userName = userName,
            )
            Result.success(response.map{
                it.toRemoteUserInfo()
            })
        }catch (e: Exception) {
            Result.failure(e)
        }
    }


    override suspend fun insertGitHubUser(gitHubUserEntity: GitHubUserEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteGitHubUser(gitHubUserEntity: GitHubUserEntity) {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): LiveData<GitHubUserEntity> {
        TODO("Not yet implemented")
    }


}