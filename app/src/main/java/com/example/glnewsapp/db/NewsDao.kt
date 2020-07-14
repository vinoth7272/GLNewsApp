package com.example.glnewsapp.db

import androidx.annotation.NonNull
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.glnewsapp.model.NewsArticle

@Dao
interface NewsDao {
    @NonNull
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg newsArticle: NewsArticle)
}