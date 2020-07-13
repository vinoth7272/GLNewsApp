package com.example.glnewsapp.model

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("status")
    val status: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("articles")
    val newsArticles: List<NewsArticle>
)
