package com.nikitabolshakov.proandroiddevelopment.data.dataSource

import com.nikitabolshakov.proandroiddevelopment.data.model.AppState

interface DataSourceLocal<T> : DataSource<T> {
    suspend fun saveToDB(appState: AppState)
}