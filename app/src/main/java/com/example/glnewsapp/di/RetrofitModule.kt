package com.example.glnewsapp.di

import com.example.glnewsapp.network.NetworkApi
import com.example.glnewsapp.utils.Constant
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {
        //Singleton Component
        getNetworkApi(getGson())
    }
}

fun getGson() = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

/**
 * Used to get the instance of Network Api
 */
private fun getNetworkApi(gson: Gson): NetworkApi {
    return Retrofit.Builder().baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(OkHttpClient.Builder().build()).build().create(NetworkApi::class.java)
}