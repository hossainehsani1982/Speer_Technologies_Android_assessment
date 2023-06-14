package com.hossain_ehs.speertechnologiesandroidassessmen.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hossain_ehs.speertechnologiesandroidassessmen.data.local.entity.GitHubUserEntity

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGitHubUser(gitHubUserEntity: GitHubUserEntity)

    @Delete
    suspend fun deleteGitHubUser(gitHubUserEntity: GitHubUserEntity)

    @Transaction
    @Query("SELECT * FROM user_table")
    fun getAllUsers(): LiveData<GitHubUserEntity>
}