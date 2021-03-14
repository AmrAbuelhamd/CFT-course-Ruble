package com.blogspot.soyamr.cft.domain.interactors

import com.blogspot.soyamr.cft.domain.model.Result

interface UpdateCurrenciesUseCase {
    suspend operator fun invoke(): Result<Unit>
}