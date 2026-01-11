package com.devsrimanth.presentation.di

import com.devsrimanth.domain.usecase.GetAuthTokenUseCase
import com.devsrimanth.presentation.feature.app.AppViewModel
import com.devsrimanth.presentation.feature.details.TravelListingDetailsViewModel
import com.devsrimanth.presentation.feature.listings.TravelListingViewModel
import com.devsrimanth.presentation.feature.register.RegisterViewModel
import com.devsrimanth.presentation.feature.signIn.SignInViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * create singleton instances here for data classes
 * single means single instance for entire application
 * factory  means everytime recreate a instance and use  for entire application
 */


val presentationModule = module {
    viewModelOf(::TravelListingViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::RegisterViewModel)
    viewModel { (itemID:String) -> TravelListingDetailsViewModel(get(), itemID) }
    viewModel { AppViewModel(get<GetAuthTokenUseCase>()) }}