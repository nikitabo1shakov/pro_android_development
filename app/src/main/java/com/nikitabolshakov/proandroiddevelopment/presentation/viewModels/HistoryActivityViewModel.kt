package com.nikitabolshakov.proandroiddevelopment.presentation.viewModels

import androidx.lifecycle.LiveData
import com.nikitabolshakov.proandroiddevelopment.data.model.AppState
import com.nikitabolshakov.proandroiddevelopment.domain.interactor.HistoryInteractor
import com.nikitabolshakov.proandroiddevelopment.presentation.viewModels.base.BaseViewModel
import com.nikitabolshakov.proandroiddevelopment.utils.parseLocalSearchResults
import kotlinx.coroutines.launch

class HistoryActivityViewModel(
    private val historyInteractor: HistoryInteractor
) : BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> = liveDataForViewToObserve

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(AppState.Loading(null))
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        _mutableLiveData.postValue(
            parseLocalSearchResults(
                historyInteractor.getData(
                    word,
                    isOnline
                )
            )
        )
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.postValue(AppState.Success(null))
        super.onCleared()
    }
}