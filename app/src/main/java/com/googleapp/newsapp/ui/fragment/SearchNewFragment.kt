package com.googleapp.newsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.googleapp.newsapp.R
import com.googleapp.newsapp.adapter.NewsAdapter
import com.googleapp.newsapp.databinding.FragmentBreakingNewsBinding
import com.googleapp.newsapp.databinding.FragmentSavedNewsBinding
import com.googleapp.newsapp.databinding.FragmentSearchNewsBinding
import com.googleapp.newsapp.ui.MainActivity
import com.googleapp.newsapp.ui.viewmodel.NewsViewModel
import com.googleapp.newsapp.util.Constants
import com.googleapp.newsapp.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewFragment : Fragment() {
    private var _binding : FragmentSearchNewsBinding? = null
    private val binding  get() = _binding!!
    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter : NewsAdapter


    val TAG = "searchNewsFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchNewsBinding.inflate(inflater,container,false)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        var job : Job? = null
        //delay job for searching so when you input text to search it will have little delay to avoid too many request
        binding.etSearch.addTextChangedListener {editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(Constants.SEARCH_NEW_DELAY_TIME)
                editable?.let {
                    if(editable.toString().isNotEmpty()){
                        viewModel.searchNews(editable.toString())
                    }
                }
            }

        }
        viewModel.searchNews.observe(viewLifecycleOwner, Observer {   response ->
            when (response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG,"An error occurred: ${message}")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }
}