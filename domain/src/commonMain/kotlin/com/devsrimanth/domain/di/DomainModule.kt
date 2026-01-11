package com.devsrimanth.domain.di

import com.devsrimanth.domain.usecase.GetAllListingUseCase
import com.devsrimanth.domain.usecase.GetAuthTokenUseCase
import com.devsrimanth.domain.usecase.RegisterUseCase
import com.devsrimanth.domain.usecase.SignInUseCase
import org.koin.dsl.module

/**
 * create singleton instances here for domain classes
 * factory  means everytime recreate a instance and use  for entire application
 */

val domainModule = module {
    factory { GetAllListingUseCase(get()) }
    factory { SignInUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { GetAuthTokenUseCase(get()) }
}
