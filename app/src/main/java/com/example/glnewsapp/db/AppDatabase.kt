package com.example.glnewsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.glnewsapp.model.NewsArticle
import com.example.glnewsapp.model.NewsSource
import com.example.glnewsapp.utils.Constant.DB_NAME
import com.example.glnewsapp.utils.DateTypeConverter


@Database(
    entities = arrayOf(NewsArticle::class, NewsSource::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao

    companion object {
        var dbInstance: AppDatabase? = null

        fun getAppDb(context: Context): AppDatabase? {
            if (dbInstance == null) {
                synchronized(AppDatabase::class.java) {
                    dbInstance =
                        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
                }
            }
            return dbInstance
        }

        fun destroyDb() {
            dbInstance = null
        }
    }
}