package com.nikitabolshakov.proandroiddevelopment.data.repository.remote

import com.nikitabolshakov.proandroiddevelopment.data.dataSource.remote.SkyengDataSourceRemote
import com.nikitabolshakov.proandroiddevelopment.data.model.SkyengDataModel

class RepositoryRemoteImpl(
    private val skyengDataSource: SkyengDataSourceRemote<List<SkyengDataModel>>
) : RepositoryRemote<List<SkyengDataModel>> {

    override suspend fun getData(word: String): List<SkyengDataModel> =
        skyengDataSource.getData(word)
}