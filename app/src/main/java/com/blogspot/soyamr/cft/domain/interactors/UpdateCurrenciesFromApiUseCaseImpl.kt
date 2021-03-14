package com.blogspot.soyamr.cft.domain.interactors

import com.blogspot.soyamr.cft.domain.Repository
import javax.inject.Inject

class UpdateCurrenciesFromApiUseCaseImpl @Inject constructor(private val repository: Repository) :
    UpdateCurrenciesFromApiUseCase {
    override suspend operator fun invoke() = repository.updateCurrenciesFromApi()
}