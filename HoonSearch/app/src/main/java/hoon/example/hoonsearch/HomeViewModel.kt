package hoon.example.hoonsearch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hoon.example.hoonsearch.network.NaverAPI
import hoon.example.hoonsearch.network.NaverSearchResponse
import hoon.example.hoonsearch.network.NaverSearchResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    val resultList = MutableLiveData<List<NaverSearchResponseItem>>()

    fun getSearchResult(key: String, searchKeyword: String) {
        NaverAPI.naverApiService.getSearch(key, searchKeyword, 20, null)
            .enqueue(object : Callback<NaverSearchResponse> {
                override fun onResponse(
                    call: Call<NaverSearchResponse>,
                    response: Response<NaverSearchResponse>
                ) {
                    val result = response.body()
                    if (result != null && response.isSuccessful) {
                        resultList.postValue(result.items)
                    }
                }

                override fun onFailure(call: Call<NaverSearchResponse>, t: Throwable) {
                    Log.d("hoon92", "err, Msg = $t")
                }

            })
    }

}

