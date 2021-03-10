package com.blogspot.soyamr.cft.data.di

import com.blogspot.soyamr.cft.data.RepositoryImpl
import com.blogspot.soyamr.cft.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun getRepo(repositoryImpl: RepositoryImpl): Repository
}