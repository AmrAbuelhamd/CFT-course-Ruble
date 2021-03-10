package com.blogspot.soyamr.cft.data.network.model


import com.google.gson.annotations.SerializedName

data class JsonResponse(
    @SerializedName("Date")
    val date: String,
    @SerializedName("PreviousDate")
    val previousDate: String,
    @SerializedName("PreviousURL")
    val previousURL: String,
    @SerializedName("Timestamp")
    val timestamp: String,
    @SerializedName("Valute")
    val currencies: String
)