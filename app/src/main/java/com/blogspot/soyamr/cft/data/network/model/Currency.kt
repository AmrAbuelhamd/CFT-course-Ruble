package com.blogspot.soyamr.cft.data.network.model


import com.blogspot.soyamr.cft.data.database.model.Currency
import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("ID")
    val id: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Nominal")
    val nominal: Int,
    @SerializedName("NumCode")
    val numCode: String,
    @SerializedName("Previous")
    val previous: Double,
    @SerializedName("Value")
    val value: Double
) {
    fun toDataBase() = Currency(charCode, id, name, 1, numCode, previous, value / nominal)
}