package com.googleapp.newsapp.repository

import com.googleapp.newsapp.api.RetrofitInstance
import com.googleapp.newsapp.db.ArticleDatabase
import com.googleapp.newsapp.model.Article

class NewsRepository(
    val db : ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode : String,pageNumber : Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery : String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)
}