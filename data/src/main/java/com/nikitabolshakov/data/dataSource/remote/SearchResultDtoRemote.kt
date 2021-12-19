package com.nikitabolshakov.data.dataSource.remote

interface SearchResultDtoRemote<T> {
    suspend fun getData(word: String): T
}