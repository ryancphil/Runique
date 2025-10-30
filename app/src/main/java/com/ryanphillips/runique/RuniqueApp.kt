package com.ryanphillips.runique

import android.app.Application
import com.ryanphillips.auth.data.di.authDataModule
import com.ryanphillips.auth.presentation.di.authViewModelModule
import com.ryanphillips.core.data.di.coreDataModule
import com.ryanphillips.core.database.di.databaseModule
import com.ryanphillips.run.location.di.locationModule
import com.ryanphillips.run.network.di.networkModule
import com.ryanphillips.run.presentation.run_overview.di.runPresentationModule
import com.ryanphillips.runique.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RuniqueApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        configureTimber()
        configureKoin()
    }

    private fun configureTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun configureKoin() {
        startKoin {
            androidLogger()
            androidContext(this@RuniqueApp)
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
                runPresentationModule,
                locationModule,
                databaseModule,
                networkModule
            )
        }
    }
}