package com.blogspot.soyamr.cft.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blogspot.soyamr.cft.data.database.dao.CurrencyDao
import com.blogspot.soyamr.cft.data.database.model.Currency


@Database(entities = [Currency::class], version = 1)
abstract class CurrencyDataBase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}