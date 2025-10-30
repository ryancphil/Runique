package com.ryanphillips.run.network.di

import com.ryanphillips.core.domain.run.RemoteRunDataSource
import com.ryanphillips.run.network.KtorRemoteRunDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    singleOf(::KtorRemoteRunDataSource).bind<RemoteRunDataSource>()
}