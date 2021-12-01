package com.nikitabolshakov.proandroiddevelopment.data.dataSource

import io.reactivex.Observable

interface DataSource<T> {
    fun getData(word: String): Observable<T>
}