package com.cocodev2500.horoscopoapp.data.network

import com.cocodev2500.horoscopoapp.BuildConfig.BASE_URL
import com.cocodev2500.horoscopoapp.data.RepositoryImpl
import com.cocodev2500.horoscopoapp.data.core.interceptor.AunthInterceptor
import com.cocodev2500.horoscopoapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit
            .Builder() //aca se define el builder de Retrofit
            .baseUrl(BASE_URL) // Base URL for the API
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()) // Gson converter for JSON parsing
            .build() // Build the Retrofit instance
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        aunthInterceptor : AunthInterceptor // Custom authentication interceptor
    ): OkHttpClient {
        val interceptor =  HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) // Logging interceptor for debugging
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor) // Add any interceptors if needed
            .addInterceptor(aunthInterceptor) // Custom authentication interceptor
            .build() // Build the OkHttpClient instance
    }


    @Provides
    fun provideHoroscopeApiService(retrofit: Retrofit): HoroscopeApiService {
        return retrofit.create(HoroscopeApiService::class.java)
    }
    @Provides
    fun provideRepository(apiService: HoroscopeApiService): Repository {
        return RepositoryImpl(apiService)
    }
}