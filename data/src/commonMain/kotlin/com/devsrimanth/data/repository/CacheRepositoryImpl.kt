package com.devsrimanth.data.repository

import com.devsrimanth.data.datasource.CacheDataSource
import com.devsrimanth.domain.repository.CacheRepository

class CacheRepositoryImpl(private val cacheDataSource: CacheDataSource) : CacheRepository {
    override suspend fun getAuthToken(): String? {
        return cacheDataSource.getAuthToken()
    }
}