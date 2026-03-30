package com.vinted.demovinted.core.network

import android.app.Application
import com.squareup.moshi.Moshi
import com.vinted.demovinted.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseApi

@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {

    @Provides
    @BaseApi
    fun provideBaseApiEndpoint(application: Application): VintedEndpoint {
        return VintedEndpoint(application.getString(R.string.base_vinted_endpoint_url))
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        @BaseApi baseVintedEndpoint: VintedEndpoint,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseVintedEndpoint.url)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}