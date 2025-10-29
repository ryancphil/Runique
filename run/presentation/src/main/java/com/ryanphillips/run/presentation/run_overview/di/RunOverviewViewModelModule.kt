package com.ryanphillips.run.presentation.run_overview.di

import com.ryanphillips.run.presentation.run_active.ActiveRunViewModel
import com.ryanphillips.run.presentation.run_overview.RunOverviewViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val runViewModelModule = module {
    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}