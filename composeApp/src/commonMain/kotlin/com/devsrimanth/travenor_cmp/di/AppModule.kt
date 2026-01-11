package com.devsrimanth.travenor_cmp.di

import com.devsrimanth.data.di.dataModule
import com.devsrimanth.domain.di.domainModule
import com.devsrimanth.presentation.di.presentationModule
import org.koin.core.module.Module

/**
 * declare all Shared Modules in single Module
 * named as  app module and start when application launching time
 */
val appModule = listOf(presentationModule, dataModule, domainModule,platformModule())

/**
 * create platform sepearted module
 */

expect fun platformModule() : Module