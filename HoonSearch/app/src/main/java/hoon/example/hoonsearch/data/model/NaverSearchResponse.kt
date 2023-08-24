package hoon.example.hoonsearch.data.model

import com.google.gson.annotations.SerializedName

data class NaverSearchResponse(
    @SerializedName("lastBuildDate")
    val lastBuildDate: String = "",
    @SerializedName("total")
    val total: Int = 0,
    @SerializedName("start")
    val start: Int = 0,
    @SerializedName("display")
    val display: Int = 0,
    @SerializedName("items")
    val items: List<NaverSearchResponseDetailItem>
)

data class NaverSearchResponseDetailItem(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("link")
    val link: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("author")
    val author: String = "",
    @SerializedName("discount")
    val discount: String = "",
    @SerializedName("publisher")
    val publisher: String = "",
    @SerializedName("pubDate")
    val pubDate: String = "",
    @SerializedName("isbn")
    val isbn: String = "",
    @SerializedName("description")
    val description: String = "",
)
