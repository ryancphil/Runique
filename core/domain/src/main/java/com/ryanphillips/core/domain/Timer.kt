package com.ryanphillips.core.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

/**
 * Using a flow to create a timer!
 */
object Timer {
    fun timeAndEmit(): Flow<Duration> {
        return flow {
            var lastEmitTime = System.currentTimeMillis() // Variable to store the time of the last emission
            while (true) {
                delay(200L) // Loop every 200ms
                val currentTime = System.currentTimeMillis() // get new current time
                val elapsedTime = currentTime - lastEmitTime // Calculate difference
                emit(elapsedTime.milliseconds) // Emit the difference in milliseconds
                lastEmitTime = currentTime // update our variable of last emission
            }
        }
    }
}