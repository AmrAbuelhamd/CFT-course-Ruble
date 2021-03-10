package com.blogspot.soyamr.cft.domain.model


data class Currency(
    val charCode: String,
    val ID: String,
    val name: String,
    val nominal: Int,
    val numCode: String,
    val previous: Double,
    val value: Double
)