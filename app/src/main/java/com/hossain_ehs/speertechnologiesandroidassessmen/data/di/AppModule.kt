package com.hossain_ehs.speertechnologiesandroidassessmen.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.hossain_ehs.speertechnologiesandroidassessmen.data.local.AppDatabase
import com.hossain_ehs.speertechnologiesandroidassessmen.data.remote.GitHubApi
import com.hossain_ehs.speertechnologiesandroidassessmen.data.remote.GitHubApi.Companion.BASE_URL
import com.hossain_ehs.speertechnologiesandroidassessmen.data.repository.RepositoryImpl
import com.hossain_ehs.speertechnologiesandroidassessmen.data.shared_preferences.PreferencesImpl
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.repository.Repository
import com.hossain_ehs.speertechnologiesandroidassessmen.domain.shared_preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Provides
    @Singleton
    fun provideSharedPreferences(
        app : Application
    ) : SharedPreferences {
        return app.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
    }
    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences) : Preferences {
        return PreferencesImpl(sharedPreferences)
    }

    private val passphrase: ByteArray = "userEnteredPassphrase".toByteArray()
    private val factory: SupportFactory = SupportFactory(passphrase)

    @Provides
    @Singleton
    fun provideApplicationDataBase(
        app: Application
    ): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "user_db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubApi(client: OkHttpClient) : GitHubApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(GitHubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppRepository(
        api: GitHubApi,
        database: AppDatabase,
    ): Repository {
        return RepositoryImpl(
            gitHubApi = api,
            appDao = database.dao
        )
    }
}