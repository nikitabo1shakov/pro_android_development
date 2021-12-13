package com.nikitabolshakov.proandroiddevelopment.data.repository.remote

interface RepositoryRemote<T> {
    suspend fun getData(word: String): T
}