package com.devsrimanth.presentation.feature.details

import com.devsrimanth.domain.model.TravelListing


data class TravelListingDetailsUiState(
    val listing: TravelListing? = null,
    val isLoading:Boolean = false,
    val errorMessage:String? = null
) {

}