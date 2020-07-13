package com.example.glnewsapp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.glnewsapp.R
import com.example.glnewsapp.model.NewsArticle
import com.example.glnewsapp.utils.AppUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_news.*
import kotlinx.android.synthetic.main.news_list_item.view.*

class DetailNewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)

//        val bundle = intent?.getBundleExtra("BUNDLE")
//        val newsArticle = bundle?.getParcelable<NewsArticle>("NEWS") as NewsArticle
        val data = intent?.extras
        val newsArticle = data?.getParcelable<NewsArticle>("NEWS") as NewsArticle
       /* if (newsArticle.imageUrl.isNullOrEmpty()) {
            img_news.visibility = View.GONE
        } else {*/
            Picasso.get().load(newsArticle.imageUrl).fit()
                .placeholder(R.drawable.gl_placeholder)
                .into(img_news)
//        }

        txt_source.text = newsArticle.source.name
        txt_date.text = AppUtil.getInstance().convertDatesIntoDays(newsArticle.publishedDate)
        txt_title.text = newsArticle.title
        txt_desc.text = newsArticle.description

        btn_back.setOnClickListener { finish() }
    }
}