package com.devsrimanth.domain.usecase

import com.devsrimanth.domain.model.TravelListing
import com.devsrimanth.domain.repository.ListingRepository

class GetAllListingUseCase(private val repository: ListingRepository) {
    suspend fun execute(): List<TravelListing> {
        val data = repository.getAllListings()
        if (data.isSuccess) {
            return data.getOrNull()!!
        } else {
            return emptyList()
        }
    }
}

