package com.yudi.test3.api.interfaces

/**
 * @author Yudi Rahmat
 */

interface ApiCallBack<in T> {
    fun onComplete() {}
    fun onStart() {}
    fun onError(error: Throwable)
    fun onSucess(response: T)
}