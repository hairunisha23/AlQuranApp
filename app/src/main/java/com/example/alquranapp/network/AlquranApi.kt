package com.example.alquranapp.network

import com.example.alquranapp.model.AllSurahResponse
import retrofit2.Call
import retrofit2.http.GET

interface AlquranApi {
    @GET("surah")
    fun getAllSurah(): Call<AllSurahResponse>
}
