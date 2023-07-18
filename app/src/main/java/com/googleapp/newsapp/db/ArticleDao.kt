package com.googleapp.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.googleapp.newsapp.model.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles ") //return live data object, can't work with suspend
    fun getAllArticle(): LiveData<List<Article>> //when data in list changes, Livedata will notify all of its observers that subcribed to change of that livedata

    @Delete
    suspend fun deleteArticle(article: Article)
}