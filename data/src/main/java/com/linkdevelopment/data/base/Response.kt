package com.linkdevelopment.data.base

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("status") var status: String? = "",
    @SerializedName("source") var source: String? = "",
    @SerializedName("sortBy") var sortBy: String? = "",
    @SerializedName("articles") var articles: MutableList<T>? = mutableListOf()
)