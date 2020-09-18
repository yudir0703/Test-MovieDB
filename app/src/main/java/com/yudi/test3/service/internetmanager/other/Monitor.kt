package com.yudi.test3.service.internetmanager.other

/**
 * @author Yudi Rahmat
 */

interface Monitor :
    LifecycleListener {
    interface ConnectivityListener {
        fun onConnectivityChanged(
            connectionType: Int,
            isConnected: Boolean,
            isFast: Boolean
        )
    }
}