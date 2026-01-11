package com.devsrimanth.domain.usecase

import com.devsrimanth.domain.repository.CacheRepository


class GetAuthTokenUseCase(private val repository: CacheRepository) {
    suspend fun execute(): String? {
        return repository.getAuthToken()
    }
}