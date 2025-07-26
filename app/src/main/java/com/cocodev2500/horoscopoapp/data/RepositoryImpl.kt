package com.cocodev2500.horoscopoapp.data

import android.util.Log
import com.cocodev2500.horoscopoapp.data.network.HoroscopeApiService
import com.cocodev2500.horoscopoapp.domain.model.PredictionModel
import com.cocodev2500.horoscopoapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: HoroscopeApiService
) : Repository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching {
            apiService.getHoroscope(sign)
        }.onSuccess { return it.toDomain() }
        .onFailure { Log.i("RepositoryImpl", "Error fetching prediction: ${it.message}") }
        return null
    }
}

