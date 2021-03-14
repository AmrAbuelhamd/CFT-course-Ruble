package com.blogspot.soyamr.cft.domain.interactors

import com.blogspot.soyamr.cft.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface GetCurrenciesUseCase {
    operator fun invoke(): Flow<List<Currency>>
}