package com.googleapp.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.googleapp.newsapp.R
import com.googleapp.newsapp.databinding.FragmentBreakingNewsBinding
import com.googleapp.newsapp.databinding.FragmentSavedNewsBinding
import com.googleapp.newsapp.ui.MainActivity
import com.googleapp.newsapp.ui.viewmodel.NewsViewModel

class SavedNewsFragment : Fragment() {
    private var _binding : FragmentSavedNewsBinding? = null
    private val binding  get() = _binding!!
    lateinit var viewModel: NewsViewModel

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
        _binding = FragmentSavedNewsBinding.inflate(inflater,container,false)
        viewModel = (activity as MainActivity).viewModel
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}