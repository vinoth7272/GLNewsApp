package com.example.glnewsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.glnewsapp.model.BaseResponse
import com.example.glnewsapp.model.NewsArticle
import com.example.glnewsapp.network.ResponseCallback
import com.example.glnewsapp.view.NewsListActivity

class NewsViewModel(val dataRepository: DataRepository) : ViewModel() {

    val responseError = MutableLiveData<String>()
    val isLoadingSuccess = MutableLiveData<Boolean>()
    var pageCount: Int = 1
    var newsArticleList = ArrayList<NewsArticle>()
    var isDataFetch = true

    fun fetchTopNews(pageNumber: Int) {
        println("GLAPP ${pageNumber}")
        dataRepository.fetchTopNewsFromApi(pageNumber, object : ResponseCallback {
            override fun onSuccess(baseResponse: BaseResponse) {
                if (baseResponse.newsArticles?.isNotEmpty()!!) {
                    baseResponse.newsArticles.let { newsArticleList.addAll(it) }
                } else {
                    isDataFetch = false
                }

                isLoadingSuccess.value = true
                pageCount += 1
            }

            override fun onFailure(exceptionMsg: String) {
                responseError.value = exceptionMsg
                isLoadingSuccess.value = false
            }

            override fun onError(baseResponse: BaseResponse) {
                responseError.value = baseResponse.message
                isLoadingSuccess.value = false
            }

        })
    }

    fun onScrollEnd(newsListActivity: NewsListActivity, layoutManager: LinearLayoutManager) {

        if (layoutManager.findLastCompletelyVisibleItemPosition() == newsArticleList.size - 1) {
            if (isDataFetch)
                fetchTopNews(pageCount)
        }
    }
}