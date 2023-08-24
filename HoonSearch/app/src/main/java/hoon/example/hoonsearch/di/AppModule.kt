package hoon.example.hoonsearch.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hoon.example.hoonsearch.BuildConfig
import hoon.example.hoonsearch.data.api.NaverSearchApi
import hoon.example.hoonsearch.util.Constants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNaverClient(): OkHttpClient {
        val headerInterceptor = Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("X-Naver-Client-Id", BuildConfig.NAVER_CLIENT_ID)
                .addHeader("X-Naver-Client-Secret", BuildConfig.NAVER_CLIENT_SERCRET)
                .build()
            it.proceed(request)
        }

        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideNaverApiService(retrofit: Retrofit): NaverSearchApi {
        return retrofit.create(NaverSearchApi::class.java)
    }

}