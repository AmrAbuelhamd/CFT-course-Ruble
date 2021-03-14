package com.blogspot.soyamr.cft.domain.interactors

import com.blogspot.soyamr.cft.domain.model.Result

interface UpdateCurrenciesFromApiUseCase {
    suspend operator fun invoke(): Result<Unit>
}