package com.example.glnewsapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class NewsSource(
    @PrimaryKey(autoGenerate = true)
    val routineId: Int,
    @ColumnInfo
    val id: String?,
    @ColumnInfo
    val name: String) : Parcelable
