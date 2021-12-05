package com.nikitabolshakov.proandroiddevelopment.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikitabolshakov.proandroiddevelopment.data.model.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData()
) : ViewModel() {

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    abstract fun getData(word: String, isOnline: Boolean)

    abstract fun handleError(error: Throwable)

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }
}