package com.blogspot.soyamr.cft.domain

import com.blogspot.soyamr.cft.domain.model.Currency
import com.blogspot.soyamr.cft.domain.model.Result

interface Repository {
    suspend fun getCurrencies(): Result<List<Currency>>
}