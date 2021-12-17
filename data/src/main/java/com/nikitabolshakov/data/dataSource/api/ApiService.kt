package com.nikitabolshakov.data.dataSource.api

import com.nikitabolshakov.model.SearchResultDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

private const val ENDPOINT_URL = "words/search"
private const val PARAMETER_SEARCH = "search"

interface ApiService {

    @GET(ENDPOINT_URL)
    fun searchAsync(@Query(PARAMETER_SEARCH) wordToSearch: String): Deferred<List<SearchResultDto>>
}