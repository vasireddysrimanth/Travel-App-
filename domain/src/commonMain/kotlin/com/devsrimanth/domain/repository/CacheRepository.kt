package com.devsrimanth.domain.repository

interface CacheRepository {
    suspend fun getAuthToken(): String?
}