package com.nikitabolshakov.proandroiddevelopment.data.dataSource

import com.nikitabolshakov.proandroiddevelopment.data.model.DataModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

private const val VALUE_URL = "words/search"
private const val VALUE_SEARCH = "search"

interface ApiService {

    @GET(VALUE_URL)
    fun searchAsync(@Query(VALUE_SEARCH) wordToSearch: String): Deferred<List<DataModel>>
}