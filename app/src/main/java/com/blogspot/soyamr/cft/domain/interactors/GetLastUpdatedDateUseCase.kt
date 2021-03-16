package com.blogspot.soyamr.cft.domain.interactors

import kotlinx.coroutines.flow.Flow

interface GetLastUpdatedDateUseCase {
    operator fun invoke(): Flow<String>
}