package com.nikitabolshakov.proandroiddevelopment.presenter

import io.reactivex.Observable

// Ещё выше стоит интерактор. Здесь уже чистая бизнес-логика.
interface Interactor<T> {
    // Use Case: получение данных для вывода на экран.
    // Используем RxJava.
    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}