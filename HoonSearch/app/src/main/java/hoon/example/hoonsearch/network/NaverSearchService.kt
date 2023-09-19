package hoon.example.hoonsearch.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hoon.example.hoonsearch.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private val BASE_URL = "https://openapi.naver.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val headerInterceptor = Interceptor {
    val request = it.request()
        .newBuilder()
        .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
        .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_CLIENT_SERCRET)
        .build()
    return@Interceptor it.proceed(request)
}

private val client = OkHttpClient.Builder()
    .addInterceptor(headerInterceptor)
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface NaverSearchService {
    @GET("v1/search/{type}.json")
    suspend fun runSearch(
        @Path("type") type: String,
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    ): Response<NaverSearchResponse>
}

object NaverSearchApi {
    val naverSearchApi : NaverSearchService by lazy {
        retrofit.create(NaverSearchService::class.java)
    }
}

fun <T : Any> Response<T>.toRetrofitResponse(): RetrofitResult<T> {
    return try {
        if (isSuccessful) {
            body()?.let {
                Log.d("hoonRetrofit", "api call success, code = ${code()}")
                RetrofitResult.Success(it, code())
            } ?: RetrofitResult.EmptyResult(code())
        } else {
            Log.d("hoonRetrofit", "api call err")
            RetrofitResult.ApiError(errorBody()?.string() ?: "Api err", code())
        }
    } catch (e: Exception) {
        e.printStackTrace()
        RetrofitResult.NetworkError(e.message ?: "Unknown err")
    }
}