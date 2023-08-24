package hoon.example.hoonsearch.data.repository

import hoon.example.hoonsearch.data.model.NaverSearchResponse

interface NaverSearchRepository {

    suspend fun runSearch(
        type: String,
        query: String,
        display: Int? = null,
        start: Int? = null
    ): NaverSearchResponse
}