package com.example.android.kotnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.android.kotnews.databinding.FragmentNewsBinding

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
        val args = NewsFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(context, args.hi, Toast.LENGTH_LONG).show()

        return binding.root
    }

}
