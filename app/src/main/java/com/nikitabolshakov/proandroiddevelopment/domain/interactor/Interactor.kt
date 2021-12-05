package com.nikitabolshakov.proandroiddevelopment.domain.interactor

interface Interactor<T> {
    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}