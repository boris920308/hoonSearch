package hoon.example.hoonsearch.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hoon.example.hoonsearch.data.repository.NaverSearchRepository
import hoon.example.hoonsearch.data.model.NaverSearchResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val naverSearchRepository: NaverSearchRepository,
) : ViewModel() {

    private val _searchFlow = MutableSharedFlow<NaverSearchResponse>()
    val searchFlow = _searchFlow.asSharedFlow()

    fun getSearchResult(key: String, searchKeyword: String) {
        viewModelScope.launch {
            try {
                _searchFlow.emit(
                    naverSearchRepository.runSearch(key, searchKeyword, 20, null)
                )
            } catch (e: Exception) {
                Log.e("hoon92", "err = $e")
            }
        }
    }

}

