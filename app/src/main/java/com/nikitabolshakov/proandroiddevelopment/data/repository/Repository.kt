package com.nikitabolshakov.proandroiddevelopment.data.repository

interface Repository<T> {
    suspend fun getData(word: String): T
}