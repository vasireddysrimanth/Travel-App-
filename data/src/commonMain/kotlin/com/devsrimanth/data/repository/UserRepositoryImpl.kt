package com.devsrimanth.data.repository

import com.devsrimanth.data.datasource.CacheDataSource
import com.devsrimanth.data.datasource.RemoteDataSource
import com.devsrimanth.data.mappers.RegisterRequestMapper
import com.devsrimanth.data.mappers.UserMapper
import com.devsrimanth.data.model.request.SignInRequest
import com.devsrimanth.domain.model.RegisterModel
import com.devsrimanth.domain.model.UserModel
import com.devsrimanth.domain.repository.UserRepository

class UserRepositoryImp(val dataSource: RemoteDataSource, private val cacheDataSource: CacheDataSource) : UserRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<UserModel> {

        return try {
            val response = dataSource.signIn(SignInRequest(email, password))
            if (response.isSuccess) {
                val response = response.getOrNull()!!
                val userModel = UserMapper.toDomain(response.user)
                cacheDataSource.saveAuthToken(response.token)
                Result.success(userModel)
            } else {
                Result.failure(Exception("Login failed with status code: ${response.exceptionOrNull()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(request: RegisterModel): Result<UserModel> {
        return try {
            val response = dataSource.register(RegisterRequestMapper.toDto(request))
            if (response.isSuccess) {
                val response = response.getOrNull()!!
                val userModel = UserMapper.toDomain(response.user)
                cacheDataSource.saveAuthToken(response.token)
                Result.success(userModel)
            } else {
                Result.failure(Exception("Registration failed with status code: ${response.exceptionOrNull()}"))
            }

        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}