package com.ryanphillips.run.domain

import com.ryanphillips.core.domain.location.LocationTimestamp
import kotlin.math.roundToInt

object LocationDataCalculator {

    /**
     * A helper function that sums the total distance in meters of our Locations.
     * Because tracking a run can be paused and resumed, we store locations as a
     * List of Lists. This adds some complexity, but handled well with creative kotlin.
     */
    fun getTotalDistanceMeters(locations: List<List<LocationTimestamp>>): Int {
        return locations.sumOf { timestampsPerLine -> // We start with sumOf our outer list (eg. "add all my lists together")
            timestampsPerLine.zipWithNext { location1, location2 -> // For each inner List, zipWithNext pairs adjacent items
                location1.location.location.distanceTo(location2.location.location) // Get the distance between the pairs
            }
                .sum() // Sum up the List<distances between all points>
                .roundToInt()
        } // sumOf the sums calculated inside this block.
    }
}