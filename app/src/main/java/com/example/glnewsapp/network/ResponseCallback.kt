package com.example.glnewsapp.network

import com.example.glnewsapp.model.BaseResponse

interface ResponseCallback {
    fun onSuccess(baseResponse: BaseResponse)
    fun onFailure(exceptionMsg : String)
    fun onError(baseResponse: BaseResponse)
}