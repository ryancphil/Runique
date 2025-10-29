package com.ryanphillips.run.presentation.run_active

import com.ryanphillips.core.presentation.ui.UiText

sealed interface ActiveRunEvent {
    data class Error(val error: UiText): ActiveRunEvent
    data object RunSaved: ActiveRunEvent

}