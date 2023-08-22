package hoon.example.hoonsearch.network

import com.google.gson.annotations.SerializedName

data class NaverSearchResponse(
    @SerializedName("total")
    val total: Int = 0,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String = "",
    @SerializedName("start")
    val start: Int = 0,
    @SerializedName("display")
    val display: Int = 0,
    @SerializedName("items")
    val items: List<NaverSearchResponseItem>
)

data class NaverSearchResponseItem(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("link")
    val link: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("thumbnail")
    val thumbnail: String = "",
    @SerializedName("pubDate")
    val pubDate: String = ""
)
