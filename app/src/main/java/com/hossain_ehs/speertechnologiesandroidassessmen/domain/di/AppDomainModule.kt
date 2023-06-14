package com.hossain_ehs.speertechnologiesandroidassessmen.domain.di

import com.hossain_ehs.speertechnologiesandroidassessmen.domain.repository.Repository
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.usecase.GetUserFollowers
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.usecase.GetUserFollowings
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.usecase.GetUser
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.usecase.GetUserInfo
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.usecase.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AppDomainModule {

    @Provides
    @ViewModelScoped
    fun provideTrackerUseCases(
        repository: Repository
    ) : UseCases{
        return UseCases(
            getUser = GetUser(repository),
            getUserFollowings = GetUserFollowings(repository),
            getUserFollowers = GetUserFollowers(repository),
            getUserInfo = GetUserInfo(repository)
        )
    }
}