package hoon.example.hoonsearch.network

sealed class RetrofitResult<T> {

    data class Success<T>(val data: T, val code: Int) : RetrofitResult<T>()
    data class EmptyResult<T>(val code: Int) : RetrofitResult<T>()
    data class ApiError<T>(val message: String, val code: Int) : RetrofitResult<T>()
    data class NetworkError<T>(val message: String) : RetrofitResult<T>()
}