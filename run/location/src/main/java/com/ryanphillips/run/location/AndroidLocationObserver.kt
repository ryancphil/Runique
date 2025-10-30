package com.ryanphillips.run.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.ryanphillips.core.domain.location.LocationWithAltitude
import com.ryanphillips.run.domain.LocationObserver
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AndroidLocationObserver(
    private val context: Context
): LocationObserver {
    private val client = LocationServices.getFusedLocationProviderClient(context)

    /**
     * This is our concrete implementation of our interface method,
     * that leverages an Android callback to get device location.
     * We also leverage [callbackFlow] to convert the callback into a flow
     * for more reactive programming approach.
     */
    override fun observeLocation(interval: Long): Flow<LocationWithAltitude> {
        return callbackFlow { // we wrap the callback related code
            val locationManager = context.getSystemService<LocationManager>()!!
            var isGpsEnabled = false
            var isNetworkEnabled = false
            while(!isGpsEnabled && !isNetworkEnabled) {
                isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

                if(!isGpsEnabled && !isNetworkEnabled) {
                    delay(3000L)
                }
            }

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                close() // if we don't have the permissions, close the flow
            } else {
                client.lastLocation.addOnSuccessListener {
                    it?.let { location ->
                        trySend(location.toLocationWithAltitude()) // trySend is like emit() for callbackFlow
                    }
                }

                val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, interval)
                    .build()

                val locationCallback = object : LocationCallback() {
                    override fun onLocationResult(result: LocationResult) {
                        super.onLocationResult(result)
                        result.locations.lastOrNull()?.let { location ->
                            trySend(location.toLocationWithAltitude()) // trySend is like emit() for callbackFlow
                        }
                    }
                }

                client.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())

                awaitClose { // unregister/cleanup the callback
                    client.removeLocationUpdates(locationCallback)
                }
            }
        }
    }
}