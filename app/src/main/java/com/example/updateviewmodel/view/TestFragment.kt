package com.example.updateviewmodel.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.updateviewmodel.databinding.FragmentTestBinding

class TestFragment : Fragment() {
    private lateinit var binding: FragmentTestBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestBinding.inflate(inflater, container, false)
        return binding.root
    }
}