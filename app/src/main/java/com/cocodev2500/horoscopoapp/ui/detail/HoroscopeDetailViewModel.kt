package com.cocodev2500.horoscopoapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cocodev2500.horoscopoapp.domain.model.HoroscopeModel
import com.cocodev2500.horoscopoapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.sin

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(
    private val getPredictionUseCase: GetPredictionUseCase
) : ViewModel(){
    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state :StateFlow<HoroscopeDetailState> = _state

    lateinit var horoscope : HoroscopeModel

    fun getHoroscope(sing: HoroscopeModel) {
        horoscope = sing
        viewModelScope.launch {
            _state.value = HoroscopeDetailState.Loading
            val responde = withContext(Dispatchers.IO){getPredictionUseCase(sing.name)}
            if (responde != null) {
                _state.value = HoroscopeDetailState.Success(responde.horoscope, responde.sign, horoscope)
            } else {
                _state.value = HoroscopeDetailState.Error("Ha ocurrido un error al obtener la predicción")
            }
        }
    }
}