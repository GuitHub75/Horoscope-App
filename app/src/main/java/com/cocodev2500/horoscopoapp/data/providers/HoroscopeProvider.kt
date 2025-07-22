package com.cocodev2500.horoscopoapp.data.providers

import com.cocodev2500.horoscopoapp.domain.model.HoroscopeInfo
import com.cocodev2500.horoscopoapp.domain.model.HoroscopeInfo.*
import javax.inject.Inject


class HoroscopeProvider @Inject constructor() {
    fun getHoroscopeList(): List<HoroscopeInfo> {
        return listOf(
            Aries, Taurus, Gemini, Cancer, Leo, Virgo,
            Libra, Scorpio, Sagittarius, Capricorn, Aquarius, Pisces
        )
    }
}