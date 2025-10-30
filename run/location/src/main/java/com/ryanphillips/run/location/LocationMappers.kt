package com.ryanphillips.run.location

import android.location.Location
import com.ryanphillips.core.domain.location.LocationWithAltitude

/**
 * Map the Android [Location] to our domain [com.ryanphillips.core.domain.location.Location] model.
 */
fun Location.toLocationWithAltitude(): LocationWithAltitude {
    return LocationWithAltitude(
        location = com.ryanphillips.core.domain.location.Location(
            lat = latitude,
            long = longitude
        ),
        altitude = altitude
    )
}