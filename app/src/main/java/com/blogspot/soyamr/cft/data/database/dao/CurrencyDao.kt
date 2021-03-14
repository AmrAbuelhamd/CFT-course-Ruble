package com.blogspot.soyamr.cft.data.database.dao

import androidx.room.*
import com.blogspot.soyamr.cft.data.database.model.Currency
import kotlinx.coroutines.flow.Flow


@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency")
    fun getAll(): Flow<List<Currency>>

    @Query("SELECT * FROM currency WHERE id like :currencyId")
    fun findCurrencyById(currencyId: String): Currency

    @Insert
    fun insertAll(vararg currencies: Currency)

    @Update
    fun update(vararg currency: Currency)

    @Delete
    fun delete(vararg currency: Currency)

    @Query("DELETE FROM currency")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM currency")
    fun getCount(): Int
}