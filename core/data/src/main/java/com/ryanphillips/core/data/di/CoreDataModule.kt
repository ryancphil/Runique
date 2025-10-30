package com.ryanphillips.core.data.di

import com.ryanphillips.core.data.auth.EncryptedSessionStorage
import com.ryanphillips.core.data.networking.HttpClientFactory
import com.ryanphillips.core.data.run.OfflineFirstRunRepository
import com.ryanphillips.core.domain.SessionStorage
import com.ryanphillips.core.domain.run.RunRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()

    singleOf(::OfflineFirstRunRepository).bind<RunRepository>()
}