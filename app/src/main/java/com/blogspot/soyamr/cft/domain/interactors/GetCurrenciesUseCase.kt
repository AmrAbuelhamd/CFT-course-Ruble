package com.blogspot.soyamr.cft.domain.interactors

import com.blogspot.soyamr.cft.domain.model.Currency
import com.blogspot.soyamr.cft.domain.model.Result

interface GetCurrenciesUseCase {
    suspend operator fun invoke(): Result<List<Currency>>
}