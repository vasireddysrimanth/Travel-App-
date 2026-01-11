package com.devsrimanth.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.devsrimanth.data.datasource.CacheDataSource
import com.devsrimanth.data.datasource.RemoteDataSource
import com.devsrimanth.data.repository.CacheRepositoryImpl
import com.devsrimanth.data.repository.ListingRepositoryImpl
import com.devsrimanth.data.repository.UserRepositoryImp
import com.devsrimanth.domain.repository.CacheRepository
import com.devsrimanth.domain.repository.ListingRepository
import com.devsrimanth.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders


/**
 * create singleton instances here for data classes
 * single means single instance for entire application
 */

val dataModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.DEFAULT
            }
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
    }

    single { RemoteDataSource(httpClient = get<HttpClient>(), get()) }
    single { CacheDataSource(dataStore = get<DataStore<Preferences>>()) }

    single<ListingRepository> {
        ListingRepositoryImpl(
            get<RemoteDataSource>()
        )
    }

    single<UserRepository> {
        UserRepositoryImp(
            get<RemoteDataSource>(), get<CacheDataSource>()
        )
    }
    single<CacheRepository> {
        CacheRepositoryImpl(get())
    }
}
