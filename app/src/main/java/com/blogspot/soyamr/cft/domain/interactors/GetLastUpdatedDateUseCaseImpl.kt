package com.blogspot.soyamr.cft.domain.interactors

import com.blogspot.soyamr.cft.domain.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastUpdatedDateUseCaseImpl @Inject constructor(private val repository: Repository) :
    GetLastUpdatedDateUseCase {
    override fun invoke(): Flow<String> = repository.getLastUpdatedString()
}