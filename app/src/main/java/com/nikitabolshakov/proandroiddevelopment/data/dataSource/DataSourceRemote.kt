package com.nikitabolshakov.proandroiddevelopment.data.dataSource

import com.nikitabolshakov.proandroiddevelopment.data.model.DataModel
import io.reactivex.Observable

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation()
) : DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}