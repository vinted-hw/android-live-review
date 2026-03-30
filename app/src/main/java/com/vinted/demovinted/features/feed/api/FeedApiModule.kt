package com.vinted.demovinted.features.feed.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object FeedApiModule {

    @Provides
    fun providesFeedApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
}