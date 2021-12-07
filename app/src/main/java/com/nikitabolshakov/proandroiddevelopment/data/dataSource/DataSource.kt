package com.nikitabolshakov.proandroiddevelopment.data.dataSource

interface DataSource<T> {
    suspend fun getData(word: String): T
}