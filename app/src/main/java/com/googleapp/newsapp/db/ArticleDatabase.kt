package com.googleapp.newsapp.db

import android.content.Context
import androidx.room.*
import com.googleapp.newsapp.model.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile  //other thread can see when a thread changes this instance
        private var instance: ArticleDatabase? = null  //make sure only instance in database
        private val LOCK = Any()

        operator fun invoke(context: Context) =
            instance ?: synchronized(LOCK) { //call when initialize
                //every thing inside block code here cant be accessed by other threads at same time
                instance ?: createDatabase(context).also {
                    instance = it
                }
            }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, ArticleDatabase::class.java, "article_db.db"
        ).build()
    }
}