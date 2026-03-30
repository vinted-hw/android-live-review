package com.vinted.demovinted.core.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vinted.demovinted.core.network.interceptor.BigDecimalAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(BigDecimalAdapter)
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}