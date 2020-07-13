package com.example.glnewsapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsSource(val id: String?, val name: String) : Parcelable
