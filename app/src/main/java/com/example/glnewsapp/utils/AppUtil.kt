package com.example.glnewsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import java.util.*

class AppUtil {

    companion object {
        private var instance: AppUtil = AppUtil()

        @Synchronized
        fun getInstance(): AppUtil {
            return instance
        }
    }

    //To Check the internet connection
    fun isConnectedToNetwork(context: Context): Boolean {
        val connectivityManager: ConnectivityManager? =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }

    fun convertDatesIntoDays(newsDate: Date): String {
        var different = Math.abs(System.currentTimeMillis() - newsDate.time)

        val secondsInMilli: Long = 1000
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24

        val elapsedDays: Long = different / daysInMilli
        different = different % daysInMilli
        var convertedString = convertString(elapsedDays,"day")
        if (checkNull(convertedString)) return convertedString
        val elapsedHours = different / hoursInMilli
        different = different % hoursInMilli
        convertedString = convertString(elapsedHours,"hour")
        if (checkNull(convertedString)) return convertedString
        val elapsedMinutes = different / minutesInMilli
        different = different % minutesInMilli
        convertedString = convertString(elapsedMinutes,"minute")
        if (checkNull(convertedString)) return convertedString
        val elapsedSeconds = different / secondsInMilli
        convertedString = convertString(elapsedSeconds,"second")
        if (checkNull(convertedString)) return convertedString
        return newsDate.toString()
    }

    private fun checkNull(convertedString: String): Boolean {
        if (convertedString.isNotEmpty())
            return true
        return false
    }

    fun convertString(elapsesCount : Long,appendString: String) : String{
        if(elapsesCount>0 && elapsesCount<=1){
            return "${elapsesCount} ${appendString} ago"
        }else if(elapsesCount>1){
            return "${elapsesCount} ${appendString}s ago"
        }
        return ""
    }
}