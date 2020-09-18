package com.yudi.test3.service.worker

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import com.yudi.test3.app.common.Constant.TAG

/**
 * Created by Yudi Rahmat
 */
class TestService : Service() {
    private lateinit var timer: CountDownTimer
    var value: Int = 1
    val timerCount: Long = 60000 // 1 minute

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "Service onCreate")

        runTimer()
    }

    private fun runTimer() {
        timer = object: CountDownTimer(timerCount, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.i(TAG, "data123 -- running")
            }
            override fun onFinish() {
                sendMyLocation()
            }
        }
        timer.start()
    }

    private fun sendMyLocation() {
        Log.i(TAG, "data123 -- $value")
        runTimer()
        value++
    }

    override fun onDestroy() {
        super.onDestroy()

        if(timer != null) timer?.cancel()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}