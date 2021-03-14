package com.blogspot.soyamr.cft.domain.interactors

import com.blogspot.soyamr.cft.domain.Repository
import javax.inject.Inject

class UpdateCurrencyNominalUseCaseImpl @Inject constructor(private val repository: Repository) :
    UpdateCurrencyNominalUseCase {
    override suspend operator fun invoke(id: String, newNominal: Int) = repository.updateCurrencyNominal(id,newNominal)
}