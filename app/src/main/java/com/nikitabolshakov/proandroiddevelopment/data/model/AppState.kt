package com.nikitabolshakov.proandroiddevelopment.data.model

sealed class AppState {
    data class Success(val data: List<SkyengDataModel>?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val loading: Int?) : AppState()
}