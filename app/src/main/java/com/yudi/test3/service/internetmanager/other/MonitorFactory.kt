package com.yudi.test3.service.internetmanager.other

import android.content.Context
import androidx.annotation.NonNull

/**
 * @author Yudi Rahmat
 */

interface MonitorFactory {
    @NonNull
    fun create(
        @NonNull context: Context?,
        connectionType: Int,
        @NonNull listener: Monitor.ConnectivityListener?
    ): Monitor?
}
