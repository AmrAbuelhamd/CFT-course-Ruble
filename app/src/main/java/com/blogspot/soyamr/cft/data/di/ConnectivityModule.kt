package com.blogspot.soyamr.cft.data.di

import com.blogspot.soyamr.cft.data.util.Connectivity
import com.blogspot.soyamr.cft.data.util.ConnectivityImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectivityModule {
    @Binds
    abstract fun getConnectivityImpl(connectivityImpl: ConnectivityImpl): Connectivity
}