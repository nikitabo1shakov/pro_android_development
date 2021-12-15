package com.nikitabolshakov.proandroiddevelopment.data.repository.local

import com.nikitabolshakov.proandroiddevelopment.data.model.AppState
import com.nikitabolshakov.proandroiddevelopment.data.repository.remote.RepositoryRemote

interface RepositoryLocal<T> : RepositoryRemote<T> {
    suspend fun saveToDB(appState: AppState)
}