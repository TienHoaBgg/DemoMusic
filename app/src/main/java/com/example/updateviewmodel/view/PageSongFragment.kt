package com.example.updateviewmodel.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.example.updateviewmodel.databinding.FragmentPageSongBinding

class PageSongFragment : Fragment() {
    private lateinit var binding: FragmentPageSongBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPageSongBinding.inflate(
            inflater, container, false
        )
        inits()
        return binding.root
    }

    private fun inits() {

        binding.vpSong.adapter = SongPageAdapter(
            //can luu y
            childFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
//        binding.tabLayout.setupWithViewPager(binding.vpSong)
    }
}