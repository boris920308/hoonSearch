package hoon.example.hoonsearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hoon.example.hoonsearch.network.NaverAPI
import hoon.example.hoonsearch.network.NaverSearchResponse
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _searchResult = MutableLiveData<NaverSearchResponse>()
    val searchResult: LiveData<NaverSearchResponse> = _searchResult

    fun getSearchResult(key: String, searchKeyword: String) {
        viewModelScope.launch {
            try {
                _searchResult.postValue(NaverAPI.naverApiService.getSearch(key, searchKeyword, 20, null))
            } catch (e: Exception) {
                Log.e("hoon92", "err = $e")
            }
        }
    }

}

