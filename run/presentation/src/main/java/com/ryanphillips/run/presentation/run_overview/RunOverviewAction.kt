package com.ryanphillips.run.presentation.run_overview

import com.ryanphillips.run.presentation.run_overview.model.RunUi

sealed interface RunOverviewAction {
    data object OnStartClick: RunOverviewAction
    data object OnAnalyticsClick: RunOverviewAction
    data object OnLogoutClick: RunOverviewAction
    data class DeleteRun(val runUi: RunUi): RunOverviewAction
}