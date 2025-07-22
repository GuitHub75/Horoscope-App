package com.cocodev2500.horoscopoapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cocodev2500.horoscopoapp.databinding.FragmentHorocopeBinding
import com.cocodev2500.horoscopoapp.domain.model.HoroscopeInfo
import com.cocodev2500.horoscopoapp.domain.model.HoroscopeModel
import com.cocodev2500.horoscopoapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HorocopeFragment : Fragment() {

    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()
    private lateinit var horoscopeAdapter: HoroscopeAdapter
    private var _binding: FragmentHorocopeBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initRecyclerView()
        initUIState()
    }

    private fun initRecyclerView() {
        horoscopeAdapter = HoroscopeAdapter( onItemSelected = {
        val type = when(it){
                HoroscopeInfo.Aquarius -> HoroscopeModel.Aquarius
                HoroscopeInfo.Aries -> HoroscopeModel.Aries
                HoroscopeInfo.Cancer -> HoroscopeModel.Cancer
                HoroscopeInfo.Capricorn -> HoroscopeModel.Capricorn
                HoroscopeInfo.Gemini -> HoroscopeModel.Geminis
                HoroscopeInfo.Leo ->   HoroscopeModel.Leo
                HoroscopeInfo.Libra -> HoroscopeModel.Libra
                HoroscopeInfo.Pisces -> HoroscopeModel.Pisces
                HoroscopeInfo.Sagittarius ->  HoroscopeModel.Sagittarius
                HoroscopeInfo.Scorpio -> HoroscopeModel.Escorpio
                HoroscopeInfo.Taurus ->  HoroscopeModel.Taurus
                HoroscopeInfo.Virgo -> HoroscopeModel.Virgo
            }
            findNavController().navigate(
                HorocopeFragmentDirections.actionHoroscopeFragmentToHoroscopeDetailActivity(type)
            )
        })
        binding.recyclerViewHoroscope.apply {
            layoutManager = GridLayoutManager(requireContext(), 2) // Cambia a GridLayoutManager para mostrar dos columnas
            adapter = horoscopeAdapter
        }
    }

    private fun initUIState() {
        //esta se engancha al ciclo de vida del fragmento
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                //observa el flujo de datos del ViewModel
                horoscopeViewModel.horoscope.collect { horoscopeList ->
                    //actualiza la lista del adaptador con los datos recibidos
                    horoscopeAdapter.updateHoroscopeList(horoscopeList)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHorocopeBinding.inflate(inflater, container, false)
        return binding.root
    }

}