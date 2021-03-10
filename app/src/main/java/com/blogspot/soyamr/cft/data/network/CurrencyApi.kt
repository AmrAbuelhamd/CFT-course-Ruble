package com.blogspot.soyamr.cft.data.network

import com.blogspot.soyamr.cft.data.network.model.JsonResponse
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyApi {

    @GET("daily_json.js")
    suspend fun getCurrencies(): String
}