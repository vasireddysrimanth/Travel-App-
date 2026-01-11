package com.devsrimanth.presentation.feature.signIn

import com.devsrimanth.domain.model.UserModel

data class SignInUiState(
    val user: UserModel? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)