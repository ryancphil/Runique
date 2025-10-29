package com.ryanphillips.auth.presentation.di

import com.ryanphillips.auth.presentation.login.LoginViewModel
import com.ryanphillips.auth.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}