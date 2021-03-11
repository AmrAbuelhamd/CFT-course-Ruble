package com.blogspot.soyamr.cft.data.network

import retrofit2.http.GET

interface CurrencyApi {

    @GET("daily_json.js")
    suspend fun getCurrencies(): String
}