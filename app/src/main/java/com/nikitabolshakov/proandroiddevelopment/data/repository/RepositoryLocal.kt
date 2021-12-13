package com.nikitabolshakov.proandroiddevelopment.data.repository

import com.nikitabolshakov.proandroiddevelopment.data.model.AppState

interface RepositoryLocal<T> : Repository<T> {
    suspend fun saveToDB(appState: AppState)
}