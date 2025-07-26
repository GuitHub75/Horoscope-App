package com.cocodev2500.horoscopoapp.ui.detail

import com.cocodev2500.horoscopoapp.domain.model.HoroscopeModel

sealed class HoroscopeDetailState{
    data object Loading: HoroscopeDetailState() // data object cuando no se pasan parámetros
    data class Success(val predition: String, val sing :String, val horoscopeModel: HoroscopeModel): HoroscopeDetailState() //data class cuando se pasan parámetros
    data class Error(val message: String): HoroscopeDetailState()
}