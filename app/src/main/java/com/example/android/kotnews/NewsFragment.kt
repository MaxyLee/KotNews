package com.example.android.kotnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.android.kotnews.data.NewsDatabase
import com.example.android.kotnews.databinding.FragmentNewsBinding
import com.example.android.kotnews.viewmodels.NewsViewModel
import com.example.android.kotnews.viewmodels.NewsViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_news, container, false
        )
        val application = requireNotNull(this.activity).application
        val args = NewsFragmentArgs.fromBundle(requireArguments())
        val dataSource = NewsDatabase.getInstance(application).newsDatabaseDao
        val viewModelFactory = NewsViewModelFactory(args.newsId, dataSource)
        val newsViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsViewModel::class.java)

        binding.viewModel = newsViewModel
        binding.setLifecycleOwner(this)

        Toast.makeText(context, args.newsId.toString(), Toast.LENGTH_LONG).show()

        return binding.root
    }

}
