package com.blogspot.soyamr.cft.domain.di

import com.blogspot.soyamr.cft.domain.interactors.GetCurrenciesUseCase
import com.blogspot.soyamr.cft.domain.interactors.GetCurrenciesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindUseCase(getCurrenciesUseCaseImpl: GetCurrenciesUseCaseImpl): GetCurrenciesUseCase
}