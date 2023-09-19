package hoon.example.hoonsearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hoon.example.hoonsearch.network.NaverSearchApi
import hoon.example.hoonsearch.network.NaverSearchResponse
import hoon.example.hoonsearch.network.RetrofitResult
import hoon.example.hoonsearch.network.toRetrofitResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _searchFlow = MutableSharedFlow<NaverSearchResponse>()
    val searchFlow = _searchFlow.asSharedFlow()

    fun getSearchResult(key: String, searchKeyword: String) {
        viewModelScope.launch {
            try {
                val callRunSearch = NaverSearchApi.naverSearchApi.runSearch(
                    key, searchKeyword, 20, null
                ).toRetrofitResponse()

                when (callRunSearch) {
                    is RetrofitResult.Success -> { _searchFlow.emit(callRunSearch.data) }
                    is RetrofitResult.EmptyResult -> {}
                    is RetrofitResult.ApiError -> {}
                    is RetrofitResult.NetworkError -> {}
                }
            } catch (e: Exception) {
                Log.e("hoon92", "err = $e")
            }
        }
    }

}

