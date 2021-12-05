package com.nikitabolshakov.proandroiddevelopment.data.dataSource

import com.nikitabolshakov.proandroiddevelopment.data.model.DataModel

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}