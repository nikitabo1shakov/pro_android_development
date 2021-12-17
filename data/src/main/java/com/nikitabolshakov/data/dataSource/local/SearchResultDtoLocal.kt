package com.nikitabolshakov.data.dataSource.local

import com.nikitabolshakov.data.dataSource.remote.SearchResultDtoRemote
import com.nikitabolshakov.model.AppState

interface SearchResultDtoLocal<T> : SearchResultDtoRemote<T> {
    suspend fun saveToDB(appState: AppState)
}