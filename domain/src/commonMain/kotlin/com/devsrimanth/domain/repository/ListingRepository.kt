package com.devsrimanth.domain.repository

import com.devsrimanth.domain.model.TravelListing
import kotlinx.coroutines.flow.Flow

interface ListingRepository {
    suspend fun getAllListings(): Result<List<TravelListing>>
}
