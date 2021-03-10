package com.blogspot.soyamr.cft.data.util

import com.blogspot.soyamr.cft.data.network.model.Currency
import com.google.gson.Gson
import org.json.JSONObject


fun getCurrencyList(json: String) :List<Currency>{
    val finalResult = mutableListOf<Currency>()

    val jsonResponse = JSONObject(json)
    val currencies = jsonResponse.getJSONObject("Valute")
    val iter: Iterator<*> = currencies.keys()
    while (iter.hasNext()) {
        val key = iter.next() as String
        val currencyJsonObject = currencies.getJSONObject(key)

        val currency: Currency = Gson().fromJson(currencyJsonObject.toString(), Currency::class.java)

        finalResult.add(currency)
    }

    return finalResult
}