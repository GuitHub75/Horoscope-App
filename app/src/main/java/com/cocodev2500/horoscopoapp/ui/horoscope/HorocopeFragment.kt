package com.cocodev2500.horoscopoapp.ui.horoscope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cocodev2500.horoscopoapp.databinding.FragmentHorocopeBinding

class HorocopeFragment : Fragment() {

    private var _binding : FragmentHorocopeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHorocopeBinding.inflate(inflater, container, false)
        return binding.root
    }
}