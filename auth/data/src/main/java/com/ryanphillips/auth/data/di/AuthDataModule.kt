package com.ryanphillips.auth.data.di

import com.ryanphillips.auth.data.AuthRepositoryImpl
import com.ryanphillips.auth.data.EmailPatternValidator
import com.ryanphillips.auth.domain.AuthRepository
import com.ryanphillips.auth.domain.PatternValidator
import com.ryanphillips.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator()
    }
    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}