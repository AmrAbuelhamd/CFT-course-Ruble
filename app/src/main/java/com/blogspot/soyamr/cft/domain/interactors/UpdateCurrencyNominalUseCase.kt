package com.blogspot.soyamr.cft.domain.interactors

import com.blogspot.soyamr.cft.domain.model.Result

interface UpdateCurrencyNominalUseCase {
    suspend operator fun invoke(id: String, newNominal: Int):Result<Unit>
}