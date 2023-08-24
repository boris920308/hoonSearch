package hoon.example.hoonsearch.data.repository

import hoon.example.hoonsearch.data.api.NaverSearchApi
import hoon.example.hoonsearch.data.model.NaverSearchResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NaverSearchRepositoryImpl @Inject constructor(
    private val api: NaverSearchApi
) : NaverSearchRepository {
    override suspend fun runSearch(
        type: String,
        query: String,
        display: Int?,
        start: Int?
    ): NaverSearchResponse {
        return api.runSearch(type, query, display, start)
    }

}