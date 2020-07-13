package com.example.glnewsapp.view

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.glnewsapp.R
import com.example.glnewsapp.model.NewsArticle
import com.example.glnewsapp.utils.AppUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsListAdapter(
    val mainActivity: NewsListActivity
) :
    RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    var newsArticleList = ArrayList<NewsArticle>()
    fun setData(newsArticleList: ArrayList<NewsArticle>) {
        this.newsArticleList = newsArticleList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_list_item, parent, false)
        )
    }

    override fun getItemCount() = newsArticleList.size


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            Toast.makeText(mainActivity, "News Position $position", Toast.LENGTH_LONG)
                .show()
            val intent = Intent(mainActivity, DetailNewsActivity::class.java)
//            val bundle = Bundle()
//            bundle.putParcelable("NEWS", newsArticleList[position])
//            intent.putExtra("BUNDLE", bundle)
            intent.putExtra("NEWS", newsArticleList[position])
            mainActivity.startActivity(intent)
        }

        holder.bind(newsArticleList[position])
    }

    class NewsViewHolder(iteView: View) : RecyclerView.ViewHolder(iteView) {
        fun bind(newsArticle: NewsArticle) {
            itemView.txt_title.text = newsArticle.title
            itemView.txt_date.text = AppUtil.getInstance().convertDatesIntoDays(newsArticle.publishedDate)
            itemView.txt_source.text = newsArticle.source.name
            /*if (newsArticle.imageUrl.isNullOrEmpty()) {
                itemView.img_news.visibility = View.GONE
            } else {*/
                Picasso.get().load(newsArticle.imageUrl).fit()
                    .placeholder(R.drawable.gl_placeholder)
                    .into(itemView.img_news)
//            }
        }

    }
}
