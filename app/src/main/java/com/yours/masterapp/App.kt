package com.yours.masterapp

import android.app.Application
import com.yours.masterapp.data.retrofitModule
import com.yours.masterapp.domain.UseCaseModules
import com.yours.masterapp.ui.list.di.calculationModule
import com.yours.masterapp.ui.list.di.listModule
import com.yours.masterapp.utils.Prefs
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(retrofitModule, UseCaseModules, listModule, calculationModule))
        }
    }
}