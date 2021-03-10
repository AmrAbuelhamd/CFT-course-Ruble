package com.blogspot.soyamr.cft.data

import android.content.Context
import com.blogspot.soyamr.cft.data.network.CurrencyApi
import com.blogspot.soyamr.cft.data.util.getCurrencyList
import com.blogspot.soyamr.cft.domain.Repository
import com.blogspot.soyamr.cft.domain.model.Currency
import com.blogspot.soyamr.cft.domain.model.Result
import com.blogspot.soyamr.cft.domain.model.Success
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    val api: CurrencyApi
) :
    Repository {

    override suspend fun getCurrencies(): Result<List<Currency>> =
        withContext(Dispatchers.IO) {
            val result = api.getCurrencies()

            val finalResult = getCurrencyList(result)

            Success(finalResult.map { it.toDomain() })

        }


}