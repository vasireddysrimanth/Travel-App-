package com.devsrimanth.presentation.feature.register

import com.devsrimanth.domain.model.UserModel

data class RegisterUiState(
    val user: UserModel? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)