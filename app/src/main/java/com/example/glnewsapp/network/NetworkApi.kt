package com.example.glnewsapp.network

import com.example.glnewsapp.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {

    @GET("v2/top-headlines")
    fun getTopNews(
        @Query("country") countryCode: String,
        @Query("page") pageNumber: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String
    ): Call<BaseResponse>
}