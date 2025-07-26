package com.cocodev2500.horoscopoapp.data.core.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AunthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
): Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().header("Authorization", "Bearer ${tokenManager.getToken()}").build()
        return chain.proceed(request)
    }
}

//token Manager+
class TokenManager @Inject constructor() {
    fun getToken(): String = "ijpegpeirjpgerg"
}