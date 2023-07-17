package com.googleapp.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.googleapp.newsapp.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

}