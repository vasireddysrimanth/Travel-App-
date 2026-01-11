package com.devsrimanth.domain.usecase

import com.devsrimanth.domain.model.UserModel
import com.devsrimanth.domain.repository.UserRepository

class SignInUseCase(private val repository: UserRepository) {
    suspend fun execute(userName: String, password: String): Result<UserModel> {
        return repository.login(userName, password)
    }
}

