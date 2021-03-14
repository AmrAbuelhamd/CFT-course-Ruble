package com.blogspot.soyamr.cft.domain.interactors

import com.blogspot.soyamr.cft.domain.Repository
import javax.inject.Inject

class GetCurrenciesUseCaseImpl @Inject constructor(private val repository: Repository) :
    GetCurrenciesUseCase {
    override operator fun invoke() = repository.getCurrencies()
}