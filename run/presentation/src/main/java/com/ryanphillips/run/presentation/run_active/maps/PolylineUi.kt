package com.ryanphillips.run.presentation.run_active.maps

import androidx.compose.ui.graphics.Color
import com.ryanphillips.core.domain.location.Location

data class PolylineUi(
    val location1: Location,
    val location2: Location,
    val color: Color
)
