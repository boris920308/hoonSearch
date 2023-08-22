package hoon.example.hoonsearch.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import hoon.example.hoonsearch.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
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

interface NaverAPIService {
    @GET("v1/search/{type}.json")
    fun getSearch(
        @Path("type") type: String,
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    ): Call<NaverSearchResponse>
}

object NaverAPI {
    val naverApiService : NaverAPIService by lazy {
        retrofit.create(NaverAPIService::class.java)
    }
}