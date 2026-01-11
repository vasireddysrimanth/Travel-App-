package com.devsrimanth.domain.repository

import com.devsrimanth.domain.model.RegisterModel
import com.devsrimanth.domain.model.UserModel

interface UserRepository {
    suspend fun login(email: String, password: String): Result<UserModel>
    suspend fun register(request : RegisterModel) : Result<UserModel>
}