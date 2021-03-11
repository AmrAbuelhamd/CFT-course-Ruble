package com.blogspot.soyamr.cft.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.blogspot.soyamr.cft.data.database.model.Currency


@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency")
    fun getAll(): List<Currency>

    @Query("SELECT * FROM currency WHERE id like :currencyId")
    fun findCurrencyById(currencyId: String): Currency

    @Insert
    fun insertAll(vararg currencies: Currency)

    @Update
    fun delete(vararg currency: Currency)

    @Query("DELETE FROM currency")
    fun deleteAll()

}