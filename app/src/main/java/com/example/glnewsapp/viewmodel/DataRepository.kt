package com.example.glnewsapp.viewmodel

import com.example.glnewsapp.model.BaseResponse
import com.example.glnewsapp.network.NetworkApi
import com.example.glnewsapp.network.ResponseCallback
import com.example.glnewsapp.utils.Constant.API_KEY
import com.example.glnewsapp.utils.Constant.COUNTRY_CODE
import com.example.glnewsapp.utils.Constant.PAGE_SIZE
import retrofit2.Call
import retrofit2.Response

class DataRepository(var networkApi: NetworkApi) {
    fun fetchTopNewsFromApi(
        pageNumber: Int,
        responseCallback: ResponseCallback
    ) {
        val call = networkApi.getTopNews(COUNTRY_CODE, pageNumber, PAGE_SIZE, API_KEY)
        call.enqueue(object : retrofit2.Callback<BaseResponse> {
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                responseCallback.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                if (response.isSuccessful) {
                    responseCallback.onSuccess(response.body() as BaseResponse)
                } else
                    responseCallback.onError(response.body() as BaseResponse)
            }
        })
    }

}
