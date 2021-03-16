package com.blogspot.soyamr.cft.domain.di

import com.blogspot.soyamr.cft.domain.interactors.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindUseCase1(getCurrencies: GetCurrenciesUseCaseImpl): GetCurrenciesUseCase

    @Binds
    @Singleton
    abstract fun bindUseCase2(updateCurrencyNominal: UpdateCurrencyNominalUseCaseImpl): UpdateCurrencyNominalUseCase

    @Binds
    @Singleton
    abstract fun bindUseCase3(updateCurrencies: UpdateCurrenciesUseCaseImpl): UpdateCurrenciesUseCase

    @Binds
    @Singleton
    abstract fun bindUseCase4(updateFromApi: UpdateCurrenciesFromApiUseCaseImpl): UpdateCurrenciesFromApiUseCase

    @Binds
    @Singleton
    abstract fun bindUseCase5(lastUpdate: GetLastUpdatedDateUseCaseImpl): GetLastUpdatedDateUseCase
}