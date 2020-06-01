package com.example.android.kotnews

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.kotnews.adapters.LikeListener
import com.example.android.kotnews.adapters.NewsAdapter
import com.example.android.kotnews.adapters.NewsListener
import com.example.android.kotnews.data.NewsDatabase
import com.example.android.kotnews.databinding.FragmentHomeBinding
import com.example.android.kotnews.viewmodels.HomeViewModel
import com.example.android.kotnews.viewmodels.HomeViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = NewsDatabase.getInstance(application).newsDatabaseDao
        val viewModelFactory = HomeViewModelFactory(dataSource, application)
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        val adapter = NewsAdapter(NewsListener { newsId ->
            homeViewModel.onNewsClicked(newsId)
        }, LikeListener { newsId ->
            homeViewModel.onLikeClicked(newsId)
        })

        binding.viewModel = homeViewModel
        binding.homeNewsList.adapter = adapter

        homeViewModel.allnews.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        homeViewModel.navigateToNews.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewsFragment(it))
                homeViewModel.onNewsViewed(it)
                homeViewModel.onNewsNavigated()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.navdrawer_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.insert_menu -> {
                homeViewModel.onInsert()
                true
            }
            R.id.clear_menu -> {
                homeViewModel.onClear()
                true
            }
            else -> NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
        }
    }

}
