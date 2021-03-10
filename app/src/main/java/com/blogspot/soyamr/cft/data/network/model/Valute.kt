package com.blogspot.soyamr.cft.data.network.model


import com.google.gson.annotations.SerializedName

data class Valute(
    @SerializedName("AMD")
    val amd: Currency,
    @SerializedName("AUD")
    val aud: Currency,
    @SerializedName("AZN")
    val cur: Currency,
    @SerializedName("BGN")
    val bgn: Currency,
    @SerializedName("BRL")
    val brl: Currency,
    @SerializedName("BYN")
    val byn: Currency,
    @SerializedName("CAD")
    val cad: Currency,
    @SerializedName("CHF")
    val chf: Currency,
    @SerializedName("CNY")
    val cny: Currency,
    @SerializedName("CZK")
    val czk: Currency,
    @SerializedName("DKK")
    val dkk: Currency,
    @SerializedName("EUR")
    val eur: Currency,
    @SerializedName("GBP")
    val gbp: Currency,
    @SerializedName("HKD")
    val hkd: Currency,
    @SerializedName("HUF")
    val huf: Currency,
    @SerializedName("INR")
    val inr: Currency,
    @SerializedName("JPY")
    val jpy: Currency,
    @SerializedName("KGS")
    val kgs: Currency,
    @SerializedName("KRW")
    val krw: Currency,
    @SerializedName("KZT")
    val kzt: Currency,
    @SerializedName("MDL")
    val mdl: Currency,
    @SerializedName("NOK")
    val nok: Currency,
    @SerializedName("PLN")
    val pln: Currency,
    @SerializedName("RON")
    val ron: Currency,
    @SerializedName("SEK")
    val sek: Currency,
    @SerializedName("SGD")
    val sgd: Currency,
    @SerializedName("TJS")
    val tjs: Currency,
    @SerializedName("TMT")
    val tmt: Currency,
    @SerializedName("TRY")
    val `try`: Currency,
    @SerializedName("UAH")
    val uah: Currency,
    @SerializedName("USD")
    val usd: Currency,
    @SerializedName("UZS")
    val uzs: Currency,
    @SerializedName("XDR")
    val xdr: Currency,
    @SerializedName("ZAR")
    val zar: Currency
)