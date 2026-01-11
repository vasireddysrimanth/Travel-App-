package com.devsrimanth.travenor_cmp.di

import android.content.Context
import com.devsrimanth.data.datasource.createDataStore
import com.devsrimanth.data.datasource.dataStoreFileName
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<String> { "http://10.0.2.2:8080" }

    single {
        createDataStore(
            producerPath = {
                get<Context>().filesDir.resolve(dataStoreFileName).absolutePath
            }
        )
    }
}