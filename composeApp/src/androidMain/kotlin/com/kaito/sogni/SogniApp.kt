package com.kaito.sogni

import android.app.Application
import com.kaito.sogni.di.initKoin
import org.koin.compose.getKoin
import org.koin.core.KoinApplication
import org.koin.core.context.loadKoinModules

class SogniApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
