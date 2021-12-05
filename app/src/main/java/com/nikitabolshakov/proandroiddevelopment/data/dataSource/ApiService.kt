package com.nikitabolshakov.proandroiddevelopment.data.dataSource

import com.nikitabolshakov.proandroiddevelopment.data.model.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>
}