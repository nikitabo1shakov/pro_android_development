package com.nikitabolshakov.data.repository.local

import com.nikitabolshakov.model.AppState
import com.nikitabolshakov.data.repository.remote.RepositoryRemote

interface RepositoryLocal<T> : RepositoryRemote<T> {
    suspend fun saveToDB(appState: AppState)
}