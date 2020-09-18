package com.yudi.test3.app.base

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil.setContentView
import com.google.android.material.snackbar.Snackbar
import com.yudi.test3.R
import com.yudi.test3.app.common.Common
import com.yudi.test3.app.common.Constant
import com.yudi.test3.databinding.BaseContainerBinding
import com.yudi.test3.service.internetmanager.InternetManager
import com.yudi.test3.service.internetmanager.other.Monitor

/**
 * @author Yudi Rahmat
 */

class BaseActivity : AppCompatActivity() {
    private var initFlag = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<BaseContainerBinding>(this, R.layout.base_container)
        connectivityMonitor(this)

    }

    private fun connectivityMonitor(activity: Activity) {
        InternetManager.from(this)?.monitor(object : Monitor.ConnectivityListener {
            override fun onConnectivityChanged(connectionType: Int, isConnected: Boolean, isFast: Boolean) {
                if(isConnected) {
                    if(initFlag) initFlag = false else
                        Snackbar.make(findViewById(android.R.id.content), Constant.CONNECTIVITY_ON, Snackbar.LENGTH_LONG)
                            .setBackgroundTint(ContextCompat.getColor(activity,R.color.colorGreen))
                            .setActionTextColor(ContextCompat.getColor(activity,R.color.colorWhite_75))
                            .show()
                } else {
                    if(initFlag) initFlag = false else
                        Snackbar.make(findViewById(android.R.id.content), Constant.CONNECTIVITY_OFF, Snackbar.LENGTH_LONG)
                            .setBackgroundTint(ContextCompat.getColor(activity,R.color.colorRed))
                            .setActionTextColor(ContextCompat.getColor(activity,R.color.colorWhite_75))
                            .show()
                }
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            Common.MY_PERMISSIONS_REQUEST_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        // obtieneLocalizacion()
                    } else {
                        Common.checkLocationPermission(this)
                    }
                    return
                }
                else{
                    Common.checkLocationPermission(this)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        try { InternetManager?.from(this)?.stop() } catch (e: Exception) { e.printStackTrace() }
    }

}