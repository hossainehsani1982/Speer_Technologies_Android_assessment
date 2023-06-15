package com.hossain_ehs.speertechnologiesandroidassessmen.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.model.RemoteGithubUserInfo
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.usecase.UseCases
import retrofit2.HttpException

class AppPagingSource (
    private val useCases: UseCases,
    private val userName: String
        )
    : PagingSource<Int, RemoteGithubUserInfo>()
{
    override fun getRefreshKey(state: PagingState<Int, RemoteGithubUserInfo>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RemoteGithubUserInfo> {
        return try {
            val currentPage = params.key ?: 1
            val response = useCases.getUserFollowers(
                userName = userName,
            )
            val data = response.body()!!
            val responseData = mutableListOf<RemoteGithubUserInfo>()
            responseData.addAll(data)
            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }
}