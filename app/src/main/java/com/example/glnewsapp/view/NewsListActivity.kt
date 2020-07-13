package com.example.glnewsapp.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.glnewsapp.R
import com.example.glnewsapp.model.BaseResponse
import com.example.glnewsapp.network.NetworkApi
import com.example.glnewsapp.utils.AppUtil
import com.example.glnewsapp.viewmodel.DataRepository
import com.example.glnewsapp.viewmodel.NewsViewModel
import com.example.glnewsapp.viewmodel.NewsViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class NewsListActivity : AppCompatActivity() {

    private lateinit var mNewsListAdapter: NewsListAdapter
    val mNewsViewModel: NewsViewModel by lazy {
        ViewModelProvider(
            this,
            NewsViewModelFactory(DataRepository(networkApi))
        ).get(NewsViewModel::class.java)
    }

    private val networkApi: NetworkApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (AppUtil.getInstance().isConnectedToNetwork(this))
            mNewsViewModel.fetchTopNews(mNewsViewModel.pageCount)
        else
            Toast.makeText(this,getString(R.string.network_error),Toast.LENGTH_LONG).show()

//        progressBar.visibility = View.VISIBLE
//        txt_error.visibility = View.GONE
        setAdapter()
        observerViewModel()
    }


    private fun setAdapter() {
        mNewsListAdapter = NewsListAdapter(this)
        news_recycler_list_view.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        news_recycler_list_view.adapter = mNewsListAdapter
        refreshAdapter()
    }

    private fun observerViewModel() {
        mNewsViewModel.baseApiResponse.observe(this, Observer {
            news_recycler_list_view.visibility = View.VISIBLE
            /*if (mNewsViewModel.newsArticleList.isEmpty()) {
                news_recycler_list_view.visibility = View.VISIBLE
                txt_error.visibility = View.GONE
                progressBar.visibility =View.GONE
            } else {
                news_recycler_list_view.visibility = View.VISIBLE
                txt_error.visibility = View.GONE
                progressBar.visibility =View.GONE
            }*/
            loadRecyclerViewData(it)
        })

        mNewsViewModel.responseError.observe(this, Observer {
            txt_error.visibility = View.VISIBLE
            txt_error.text = it
        })

        mNewsViewModel.isLoading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
            if (it) {
                news_recycler_list_view.visibility = View.GONE
                txt_error.visibility = View.GONE
            }else{
                news_recycler_list_view.visibility = View.VISIBLE
            }
        })
    }

    private fun loadRecyclerViewData(baseResponse: BaseResponse) {
        mNewsViewModel.newsArticleList.addAll(baseResponse.newsArticles)
        refreshAdapter()
    }

    private fun refreshAdapter() {
        mNewsListAdapter.setData(mNewsViewModel.newsArticleList)
        mNewsListAdapter.notifyDataSetChanged()
    }
}
