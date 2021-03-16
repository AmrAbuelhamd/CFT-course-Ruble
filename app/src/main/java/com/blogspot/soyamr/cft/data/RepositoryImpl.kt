package com.blogspot.soyamr.cft.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.blogspot.soyamr.cft.data.database.dao.CurrencyDao
import com.blogspot.soyamr.cft.data.network.CurrencyApi
import com.blogspot.soyamr.cft.data.util.*
import com.blogspot.soyamr.cft.domain.Repository
import com.blogspot.soyamr.cft.domain.model.*
import com.blogspot.soyamr.cft.domain.model.Currency
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import com.blogspot.soyamr.cft.data.network.model.Currency as dbCurrency


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SHARED_PREF_NAME)
private val dateKey = stringPreferencesKey(LAST_UPDATED_STRING)

class RepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val currencyDao: CurrencyDao,
    private val api: CurrencyApi,
    private val connectivity: Connectivity
) :
    Repository {


    //get flow/observable data from database
    override fun getCurrencies(): Flow<List<Currency>> =
        currencyDao.getAll().map { list -> list.map { it.toDomain() } }

    //update database if it's empty only
    override suspend fun updateCurrencies(): Result<Unit> =
        withContext(Dispatchers.IO) {
            val rowsInDataBase = currencyDao.getCount()
            if (rowsInDataBase <= 0) {
                return@withContext updateDataBaseFromApi()
            } else {
                return@withContext Success(Unit)
            }
        }

    //remove all data from data base and fetch new data
    override suspend fun updateCurrenciesFromApi() =
        withContext(Dispatchers.IO) { updateDataBaseFromApi() }


    override suspend fun updateCurrencyNominal(id: String, newNominal: Int): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                val currency = currencyDao.findCurrencyById(id)
                currencyDao.update(currency.copy(nominal = newNominal))
            } catch (e: Exception) {
                return@withContext Failure(HttpError(Throwable(ERROR_UPDATING_CURRENCY)))//fixme use exception class
            }
            return@withContext Success(Unit)
        }

    override fun getLastUpdatedString(): Flow<String> =
        context.dataStore.data
            .map { date ->
                date[dateKey] ?: "nothing"
            }


    private suspend fun updateDataBaseFromApi(): Result<Unit> {
        if (connectivity.hasNetworkAccess()) {
            val result = try {
                api.getCurrencies()
            } catch (e: Exception) {
                return Failure(HttpError(Throwable(e.message)))
            }
            val finalResult = getCurrencyList(result)
            updateLastUpdatedString()
            insertDataIntoDataBase(finalResult)
            return Success(Unit)
        } else {
            return Failure(HttpError(Throwable(NO_INTERNET_CONNECTION)))
        }
    }

    private suspend fun updateLastUpdatedString() {
        val currentTime = getCurrentData()
        context.dataStore.edit { date ->
            date[dateKey] = currentTime
        }
    }


    private fun insertDataIntoDataBase(finalResult: List<dbCurrency>) {
        currencyDao.deleteAll()
        currencyDao.insertAll(*finalResult.map { it.toDataBase() }.toTypedArray())
    }
}
