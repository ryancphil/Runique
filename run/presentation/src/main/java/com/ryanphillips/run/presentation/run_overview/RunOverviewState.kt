package com.ryanphillips.run.presentation.run_overview

import com.ryanphillips.run.presentation.run_overview.model.RunUi

data class RunOverviewState(
    val runs: List<RunUi> = emptyList()
)
