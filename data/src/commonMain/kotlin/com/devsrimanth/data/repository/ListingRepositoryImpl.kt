package com.devsrimanth.data.repository

import com.devsrimanth.data.datasource.RemoteDataSource
import com.devsrimanth.data.mappers.TravelListingMapper
import com.devsrimanth.domain.model.TravelListing
import com.devsrimanth.domain.repository.ListingRepository

class ListingRepositoryImpl(val dataSource: RemoteDataSource) : ListingRepository {

    override suspend fun getAllListings(): Result<List<TravelListing>> {
        val dtos = dataSource.getAllListing()
        if (dtos.isSuccess) {
            val listings = dtos.getOrNull()!!.listings
            val models = TravelListingMapper.toDomain(listings)
            return Result.success(models)
        } else {
            throw dtos.exceptionOrNull()!!
        }
    }
}