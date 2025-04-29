package com.example.alquranapp.api

import com.example.alquranapp.model.AllSurahResponse
import com.example.alquranapp.model.SurahDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v1/surah")
    fun getAllSurah(): Call<AllSurahResponse>

    @GET("v1/surah/{number}")
    fun getSurah(@Path("number") number: Int): Call<AllSurahResponse>

    @GET("surah/{number}")
    suspend fun getSurahDetail(@Path("number") number: Int): SurahDetailResponse
}
