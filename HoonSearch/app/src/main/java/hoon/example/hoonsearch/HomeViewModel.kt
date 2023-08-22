package hoon.example.hoonsearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hoon.example.hoonsearch.network.NaverSearchApi
import hoon.example.hoonsearch.network.NaverSearchResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _searchFlow = MutableSharedFlow<NaverSearchResponse>()
    val searchFlow = _searchFlow.asSharedFlow()

    fun getSearchResult(key: String, searchKeyword: String) {
        viewModelScope.launch {
            try {
                _searchFlow.emit(NaverSearchApi.naverSearchApi.runSearch(key, searchKeyword, 20, null))
            } catch (e: Exception) {
                Log.e("hoon92", "err = $e")
            }
        }
    }

}

