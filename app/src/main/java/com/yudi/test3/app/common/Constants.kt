package com.yudi.test3.app.common

/**
 * @author Yudi Rahmat
 */

object Constant {
    const val TAG               = "TestApp"
    const val BASE_URL_OLD      = "https://api.github.com/"
    const val APIKEY            = "65d49b656fbc274f30a7b6dc2ae426cd"

    const val CONNECTIVITY_ON   = "Online Kembali"
    const val CONNECTIVITY_OFF  = "Kamu Sedang Offline"

    @JvmField val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence = "Verbose WorkManager Notifications"
    const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION = "Shows notifications whenever work starts"
    @JvmField val NOTIFICATION_TITLE: CharSequence = "WorkRequest Starting"
    const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
}