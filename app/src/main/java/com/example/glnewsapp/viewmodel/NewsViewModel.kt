package com.example.glnewsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.glnewsapp.model.BaseResponse
import com.example.glnewsapp.model.NewsArticle
import com.example.glnewsapp.network.ResponseCallback

class NewsViewModel(val dataRepository: DataRepository) : ViewModel() {

    val baseApiResponse = MutableLiveData<BaseResponse>()
    val responseError = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    val pageCount: Int = 1
    var newsArticleList = ArrayList<NewsArticle>()

    fun fetchTopNews(pageCount: Int) {
        isLoading.value = true
        dataRepository.fetchTopNewsFromApi(pageCount, object : ResponseCallback {
            override fun onSuccess(baseResponse: BaseResponse) {
                baseApiResponse.value = baseResponse
                isLoading.value = false
            }

            override fun onFailure(exceptionMsg: String) {
                responseError.value = exceptionMsg
                isLoading.value = false
            }

            override fun onError(baseResponse: BaseResponse) {
                responseError.value = baseResponse.message
                isLoading.value = false
            }

        })
    }
}