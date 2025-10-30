package com.ryanphillips.run.presentation.run_overview.di

import com.ryanphillips.run.domain.RunningTracker
import com.ryanphillips.run.presentation.run_active.ActiveRunViewModel
import com.ryanphillips.run.presentation.run_overview.RunOverviewViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val runPresentationModule = module {
    singleOf(::RunningTracker)

    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}