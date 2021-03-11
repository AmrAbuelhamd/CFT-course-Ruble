package com.blogspot.soyamr.cft.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blogspot.soyamr.cft.domain.model.Currency

@Entity
data class Currency(
    val charCode: String,
    @PrimaryKey val id: String,
    val name: String,
    val nominal: Int,
    val numCode: String,
    val previous: Double,
    val value: Double
){
    fun toDomain() = Currency(charCode, id, name, nominal, numCode, previous, value)
}