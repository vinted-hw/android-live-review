package com.vinted.demovinted.core.currency

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CurrencyFormatterModule {

    @Binds
    fun bindCurrencyFormatter(impl: DefaultCurrencyFormatter): CurrencyFormatter
}