package com.googleapp.newsapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.googleapp.newsapp.repository.NewsRepository
import com.googleapp.newsapp.ui.viewmodel.NewsViewModel

class NewsViewModelProviderFactory(
    val app : Application,
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app,newsRepository) as T
    }

}