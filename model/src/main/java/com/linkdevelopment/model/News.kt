package com.linkdevelopment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class News(
    var id: String? = UUID.randomUUID().toString(),
    @SerializedName("author") var author: String? = "",
    @SerializedName("title") var title: String? = "",
    @SerializedName("description") var description: String? = "",
    @SerializedName("url") var url: String? = "",
    @SerializedName("urlToImage") var urlToImage: String? = "",
    @SerializedName("publishedAt") var publishedAt: String? = ""
): Serializable