package com.tencent.karaoke.puppy.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

open class BaseViewModel : ViewModel() {

    fun <T : Any> launchRequestWithFlow(
        liveData: MutableLiveData<T>? = null,
        success: ((T?) -> Unit)? = null,
        fail: ((code: String, e: Throwable?) -> Unit)? = null,
        request: suspend () -> BaseData<T>,
    ) {
        viewModelScope.launch {
            flow<BaseData<T>> {
                val baseData = request()
                emit(baseData)
            }.flowOn(Dispatchers.IO)
                .catch { exception ->
                    fail?.invoke("", exception)
                }
                .collect {
                    if (it.status == "success") {
                        success?.invoke(it.message)
                        it.message.apply {
                            liveData?.postValue(this)
                        }
                    } else {
                        fail?.invoke(it.status, null)
                    }

                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}

