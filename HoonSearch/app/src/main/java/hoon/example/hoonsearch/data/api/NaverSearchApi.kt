package hoon.example.hoonsearch.data.api

import hoon.example.hoonsearch.data.model.NaverSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NaverSearchApi {
    @GET("v1/search/{type}.json")
    suspend fun runSearch(
        @Path("type") type: String,
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    ): NaverSearchResponse
}