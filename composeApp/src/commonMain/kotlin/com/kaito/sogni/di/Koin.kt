package com.kaito.sogni.di

import com.kaito.sogni.data.repository.IImageRepo
import com.kaito.sogni.data.repository.ISettingRepo
import com.kaito.sogni.data.repository.ImageRepoImpl
import com.kaito.sogni.data.repository.SettingRepoImpl
import com.kaito.sogni.screens.AppVM
import com.kaito.sogni.screens.splash.SplashVM
import com.russhwolf.settings.Settings
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(json, contentType = ContentType.Application.Json)
            }
        }
    }

    single<ISettingRepo> {
        SettingRepoImpl(Settings())
    }

    single<IImageRepo> {
        ImageRepoImpl(get())
    }
}

val viewModelModule = module {
    single {
        SplashVM(get())
    }
    single {
        AppVM(get())
    }
}

fun initKoin() {
    startKoin {
        modules(
            dataModule,
            viewModelModule,
        )
    }
}
