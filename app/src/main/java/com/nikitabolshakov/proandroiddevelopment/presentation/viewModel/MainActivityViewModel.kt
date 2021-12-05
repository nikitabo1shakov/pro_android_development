package com.nikitabolshakov.proandroiddevelopment.presentation.viewModel

import androidx.lifecycle.LiveData
import com.nikitabolshakov.proandroiddevelopment.data.model.AppState
import com.nikitabolshakov.proandroiddevelopment.domain.interactor.MainInteractor
import com.nikitabolshakov.proandroiddevelopment.presentation.base.BaseViewModel
import com.nikitabolshakov.proandroiddevelopment.utils.parseSearchResults
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(
    private val interactor: MainInteractor
) : BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(AppState.Loading(null))
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _mutableLiveData.postValue(parseSearchResults(interactor.getData(word, isOnline)))
        }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.postValue(AppState.Success(null))
        super.onCleared()
    }
}