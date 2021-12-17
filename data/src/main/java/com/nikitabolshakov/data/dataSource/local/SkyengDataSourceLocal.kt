package com.nikitabolshakov.data.dataSource.local

import com.nikitabolshakov.data.dataSource.remote.SkyengDataSourceRemote
import com.nikitabolshakov.model.AppState

interface SkyengDataSourceLocal<T> : SkyengDataSourceRemote<T> {
    suspend fun saveToDB(appState: AppState)
}