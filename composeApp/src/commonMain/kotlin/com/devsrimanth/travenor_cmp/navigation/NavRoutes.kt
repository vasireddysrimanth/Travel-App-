package com.devsrimanth.travenor_cmp.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoutes : NavKey {

    @Serializable
    data object Login : NavRoutes

    @Serializable
    data object SignUp : NavRoutes

    @Serializable
    data object Listing : NavRoutes

    @Serializable
    data class ListingDetails(val id: String) : NavRoutes
}
