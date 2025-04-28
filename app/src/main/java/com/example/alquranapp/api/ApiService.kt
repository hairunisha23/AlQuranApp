package com.example.alquranapp.api

import com.example.alquranapp.model.AllSurahResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v1/surah")
    fun getAllSurah(): Call<AllSurahResponse>

    @GET("v1/surah/{number}")
    fun getSurah(@Path("number") number: Int): Call<AllSurahResponse>

}