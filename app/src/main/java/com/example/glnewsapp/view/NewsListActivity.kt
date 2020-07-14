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
import com.example.glnewsapp.model.NewsArticle
import com.example.glnewsapp.network.NetworkApi
import com.example.glnewsapp.utils.AppUtil
import com.example.glnewsapp.viewmodel.DataRepository
import com.example.glnewsapp.viewmodel.NewsViewModel
import com.example.glnewsapp.viewmodel.NewsViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class NewsListActivity : AppCompatActivity() {

    private lateinit var layoutManager: LinearLayoutManager
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

        if (AppUtil.getInstance().isConnectedToNetwork(this)) {
            println("GLAPP Activity ${mNewsViewModel.pageCount}")
            mNewsViewModel.fetchTopNews(mNewsViewModel.pageCount)
        } else
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
        setAdapter()
        initScrollListener()
        observerViewModel()
    }


    private fun initScrollListener() {
        news_recycler_list_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                mNewsViewModel.onScrollEnd(this@NewsListActivity, layoutManager)
            }

        })
    }


    private fun setAdapter() {
        mNewsListAdapter = NewsListAdapter(this)
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        news_recycler_list_view.layoutManager = layoutManager
        news_recycler_list_view.adapter = mNewsListAdapter
    }

    private fun observerViewModel() {

        mNewsViewModel.responseError.observe(this, Observer {
            if (mNewsViewModel.newsArticleList.isEmpty()) {
                txt_error.visibility = View.VISIBLE
                txt_error.text = it
            }
            progressBar.visibility = View.GONE
        })

        mNewsViewModel.isLoadingSuccess.observe(this, Observer {
            if (it) {
                news_recycler_list_view.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                txt_error.visibility = View.GONE
                refreshAdapter(mNewsViewModel.newsArticleList)
            }
        })
    }


    private fun refreshAdapter(newsArticle: List<NewsArticle>) {
        mNewsListAdapter.setData(newsArticle as ArrayList<NewsArticle>)
        mNewsListAdapter.notifyDataSetChanged()
    }
}
