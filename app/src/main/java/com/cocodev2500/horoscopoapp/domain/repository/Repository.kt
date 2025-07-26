package com.cocodev2500.horoscopoapp.domain.repository
import com.cocodev2500.horoscopoapp.domain.model.PredictionModel

interface Repository {
    suspend fun getPrediction(sign: String): PredictionModel?
}