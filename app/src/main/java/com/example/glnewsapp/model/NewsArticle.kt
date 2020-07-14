package com.example.glnewsapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
data class NewsArticle(
    @SerializedName("source")
    @Embedded
    val source: NewsSource?,
    @SerializedName("title")
    @ColumnInfo
    val title: String?,
    @SerializedName("description")
    @ColumnInfo
    val description: String?,
    @SerializedName("urlToImage")
    @ColumnInfo
    val imageUrl: String?,
    @SerializedName("publishedAt")
    @PrimaryKey
    val publishedDate: Date
) : Parcelable
