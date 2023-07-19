package com.googleapp.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.googleapp.newsapp.R
import com.googleapp.newsapp.databinding.ActivityMainBinding
import com.googleapp.newsapp.db.ArticleDatabase
import com.googleapp.newsapp.repository.NewsRepository
import com.googleapp.newsapp.ui.viewmodel.NewsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val repository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application,repository)
        val newsNavHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        binding.bottomNavigationView.setupWithNavController(newsNavHostFragment!!.findNavController())
    }
}