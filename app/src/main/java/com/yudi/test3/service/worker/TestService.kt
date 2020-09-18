package com.yudi.test3.service.worker

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.yudi.test3.app.common.Constant.TAG
import com.yudi.test3.service.eventbus.EventbusLocation
import org.greenrobot.eventbus.EventBus


/**
 * Created by Yudi Rahmat
 */
class TestService : Service() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var mLocationManager: LocationManager? = null

    private lateinit var timer: CountDownTimer
    val timerCount: Long = 60000 // 1 minute

    companion object {
        var myLocation: Location? = null
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Service onCreate")

        initLocation()
        runTimer()
    }

    @SuppressLint("MissingPermission")
    private fun initLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        initializeLocationManager()
        try {
            mLocationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, mLocationListeners[1])
        } catch (ex: SecurityException) {
            Log.i(TAG, "fail to request location update, ignore", ex)
        } catch (ex: IllegalArgumentException) {
            Log.d(TAG, "network provider does not exist, " + ex.message)
        }
        try {
            mLocationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, mLocationListeners[0])
        } catch (ex: SecurityException) {
            Log.i(TAG, "fail to request location update, ignore", ex)
        } catch (ex: IllegalArgumentException) {
            Log.d(TAG, "gps provider does not exist " + ex.message)
        }

//        fusedLocationClient.lastLocation.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val currentLocation = task.result
//                myLocation = currentLocation
//            }
//        }
    }

    private fun setEventBus() {
        val data = EventbusLocation()
        data.latitude = myLocation?.latitude
        data.longitude = myLocation?.longitude

        EventBus.getDefault().post(data)
    }

    private fun runTimer() {
        timer = object: CountDownTimer(timerCount, 1000) {
            override fun onTick(millisUntilFinished: Long) { }

            override fun onFinish() {
                sendMyLocation()
            }
        }
        timer.start()
    }

    private fun sendMyLocation() {
        setEventBus()
        runTimer()
    }

    override fun onDestroy() {
        super.onDestroy()

        if(timer != null) timer?.cancel()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    /* Location */
    class LocationListener(provider: String) :
        android.location.LocationListener {
        var mLastLocation: Location
        override fun onLocationChanged(location: Location) {
            myLocation = location
            Log.e(TAG, "onLocationChanged: $location")
            mLastLocation.set(location)
        }

        override fun onProviderDisabled(provider: String) {
            Log.e(TAG, "onProviderDisabled: $provider")
        }

        override fun onProviderEnabled(provider: String) {
            Log.e(TAG, "onProviderEnabled: $provider")
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
            Log.e(TAG, "onStatusChanged: $provider")
        }

        init {
            Log.e(TAG, "LocationListener $provider")
            mLastLocation = Location(provider)
        }
    }

    private fun initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager")
        if (mLocationManager == null) {
            mLocationManager =
                applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
    }

    var mLocationListeners = arrayOf(
        LocationListener(LocationManager.GPS_PROVIDER),
        LocationListener(LocationManager.NETWORK_PROVIDER)
    )
    /* [End] Location */
}