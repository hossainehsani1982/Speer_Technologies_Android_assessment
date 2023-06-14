package com.hossain_ehs.speertechnologiesandroidassessmen.data.remote

import com.hossain_ehs.speertechnologiesandroidassessmen.data.remote.dto.FollowersDto
import com.hossain_ehs.speertechnologiesandroidassessmen.data.remote.dto.FollowingDto
import com.hossain_ehs.speertechnologiesandroidassessmen.data.remote.dto.Item
import com.hossain_ehs.speertechnologiesandroidassessmen.data.remote.dto.SearchDto
import com.hossain_ehs.speertechnologiesandroidassessmen.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {


    companion object{
        const val BASE_URL = "https://api.github.com"
    }

    //search for users with similar name
    @GET("/search/users")
    suspend fun getUser(
        @Query("q") userName : String
    ) : SearchDto

    //search for user with exact name
    @GET("/users/{username}")
    suspend fun getUserInfo(
        @Path("username") userName : String
    ) : UserDto

    //get followers of a user
    @GET("/users/{username}/followers")
    suspend fun getUserFollowers(
        @Path("username") userName : String
    ) : List<UserDto>

    //get following of a user
    @GET("/users/{username}/following")
    suspend fun getUserFollowings(
        @Path("username") userName : String
    ) : List<UserDto>
}