package com.nikitabolshakov.proandroiddevelopment.data.dataSource.local

import com.nikitabolshakov.proandroiddevelopment.data.dataSource.remote.SkyengDataSourceRemote
import com.nikitabolshakov.proandroiddevelopment.data.model.AppState

interface SkyengDataSourceLocal<T> : SkyengDataSourceRemote<T> {
    suspend fun saveToDB(appState: AppState)
}