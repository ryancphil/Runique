package com.ryanphillips.core.data.di

import com.ryanphillips.core.data.auth.EncryptedSessionStorage
import com.ryanphillips.core.data.networking.HttpClientFactory
import com.ryanphillips.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}