package com.blogspot.soyamr.cft.data

import com.blogspot.soyamr.cft.data.database.dao.CurrencyDao
import com.blogspot.soyamr.cft.data.network.CurrencyApi
import com.blogspot.soyamr.cft.data.util.Connectivity
import com.blogspot.soyamr.cft.data.util.NO_INTERNET_CONNECTION
import com.blogspot.soyamr.cft.data.util.getCurrencyList
import com.blogspot.soyamr.cft.domain.Repository
import com.blogspot.soyamr.cft.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val api: CurrencyApi,
    private val connectivity: Connectivity
) :
    Repository {

    //first get from database
    //if null then check internet and get from internet and update database
    override suspend fun getCurrencies(): Result<List<Currency>> =
        withContext(Dispatchers.IO) {
            val currencies = currencyDao.getAll()
            if (currencies.isNullOrEmpty()) {
                if (connectivity.hasNetworkAccess()) {
                    val result = try {
                        api.getCurrencies()
                    } catch (e: Exception) {
                        return@withContext Failure(HttpError(Throwable(e.message)))
                    }
                    val finalResult = getCurrencyList(result)
                    currencyDao.deleteAll()
                    currencyDao.insertAll(*finalResult.map { it.toDataBase() }.toTypedArray())
                    return@withContext Success(currencyDao.getAll().map { it.toDomain() })
                } else {
                    return@withContext Failure(HttpError(Throwable(NO_INTERNET_CONNECTION)))
                }
            } else {
                return@withContext Success(currencies.map { it.toDomain() })
            }
        }


}