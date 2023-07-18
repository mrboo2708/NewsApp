package com.googleapp.newsapp.repository

import com.googleapp.newsapp.api.RetrofitInstance
import com.googleapp.newsapp.db.ArticleDatabase
import com.googleapp.newsapp.model.Article
// nes repository to get data from database and remote data sources(retrofit,api) => have query API
//in new viewmodel we call function from repository and handle response then we will have live data objects that notify the fragment about changes
class NewsRepository(
    val db : ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode : String,pageNumber: Int) =  //network function is a suspend function
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)
}