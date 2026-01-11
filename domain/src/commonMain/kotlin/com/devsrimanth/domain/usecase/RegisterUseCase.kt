package com.devsrimanth.domain.usecase

import com.devsrimanth.domain.model.RegisterModel
import com.devsrimanth.domain.model.UserModel
import com.devsrimanth.domain.repository.UserRepository

class RegisterUseCase(private val repository: UserRepository) {
    suspend fun execute(request: RegisterModel): Result<UserModel> {
        return repository.register(request)
    }
}