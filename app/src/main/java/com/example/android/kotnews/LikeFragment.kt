package com.example.android.kotnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.kotnews.databinding.FragmentLikeBinding

/**
 * A simple [Fragment] subclass.
 */
class LikeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLikeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_like, container, false
        )

        return binding.root
    }

}
