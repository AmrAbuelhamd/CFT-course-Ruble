package com.blogspot.soyamr.cft.data.di

import android.content.Context
import androidx.room.Room
import com.blogspot.soyamr.cft.data.database.CurrencyDataBase
import com.blogspot.soyamr.cft.data.database.dao.CurrencyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun getDataBaseObject(@ApplicationContext context: Context): CurrencyDataBase =
        Room.databaseBuilder(
            context,
            CurrencyDataBase::class.java, "currency-database"
        ).build()

    @Provides
    @Singleton
    fun getCurrencyDao(db: CurrencyDataBase): CurrencyDao = db.currencyDao()

}