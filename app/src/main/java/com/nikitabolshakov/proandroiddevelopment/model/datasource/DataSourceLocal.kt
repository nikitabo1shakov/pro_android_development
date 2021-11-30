package com.nikitabolshakov.proandroiddevelopment.model.datasource

import com.nikitabolshakov.proandroiddevelopment.model.data.DataModel
import io.reactivex.Observable

// Для локальных данных используется Room.
class DataSourceLocal(
    private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()
) : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}