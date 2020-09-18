package com.yudi.test3.app.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yudi.test3.api.interfaces.Unsubscribe
import com.yudi.test3.api.models.other.Errors

/**
 * @author Yudi Rahmat
 */

open class BaseViewModel<T : Unsubscribe>(private val clientRepository: T) : ViewModel() {
    val errors: MutableLiveData<Errors> = MutableLiveData()

    override fun onCleared() {
        clientRepository.unSubscribe()
        super.onCleared()
    }


    protected fun processError(error: Throwable) {
        val er = ArrayList<String>(1)
        er.add(error.message ?: "Error Identification")
        errors.value = Errors(er)
    }

    fun errorsClear() {
        errors.value =
            Errors(arrayListOf())
    }


}