package com.blogspot.soyamr.cft.domain

import com.blogspot.soyamr.cft.domain.model.Currency
import com.blogspot.soyamr.cft.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getCurrencies(): Flow<List<Currency>>
    suspend fun updateCurrencies(): Result<Unit>
    suspend fun updateCurrenciesFromApi(): Result<Unit>
    suspend fun updateCurrencyNominal(id: String, newNominal: Int): Result<Unit>
    fun getLastUpdatedString(): Flow<String>
}