package com.hossain_ehs.speertechnologiesandroidassessmen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hossain_ehs.speertechnologiesandroidassessmen.data.local.entity.GitHubUserEntity

@Database(
    entities = [GitHubUserEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){
    abstract val dao : AppDao
}