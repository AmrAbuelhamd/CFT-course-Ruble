package com.blogspot.soyamr.cft.domain.interactors

import com.blogspot.soyamr.cft.domain.Repository
import javax.inject.Inject

class UpdateCurrenciesUseCaseImpl @Inject constructor(private val repository: Repository) :
    UpdateCurrenciesUseCase {
    override suspend operator fun invoke() = repository.updateCurrencies()
}