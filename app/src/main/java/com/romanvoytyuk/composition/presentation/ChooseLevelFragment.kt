package com.romanvoytyuk.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.romanvoytyuk.composition.R
import com.romanvoytyuk.composition.databinding.FragmentChooseLevelBinding


class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLevelEasy.setOnClickListener {  }
        binding.buttonLevelTest.setOnClickListener {  }
        binding.buttonLevelNormal.setOnClickListener {  }
        binding.buttonLevelHard.setOnClickListener {  }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}