package com.augieafr.postsapp.injection

import android.content.Context
import com.augieafr.postsapp.data.source.PostRepository
import com.augieafr.postsapp.data.source.local.LocalDataSource
import com.augieafr.postsapp.data.source.local.room.PostDatabase
import com.augieafr.postsapp.data.source.remote.RemoteDataSource
import com.augieafr.postsapp.data.source.remote.network.ApiConfig
import com.augieafr.postsapp.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): PostRepository {
        val localDatabase = PostDatabase.getInstance(context)

        val apiService = ApiConfig.getApiService()
        val remoteDataSource = RemoteDataSource.getInstance(apiService)
        val localDataSource = LocalDataSource.getInstance(localDatabase.postDao())
        val appExecutors = AppExecutors()

        return PostRepository(remoteDataSource, localDataSource, appExecutors)
    }
}