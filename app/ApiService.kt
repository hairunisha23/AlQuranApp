package com.example.alquranapp.api

import com.example.alquranapp.model.Surah
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v1/surah/{number}/quran-simple")
    fun getSurah(@Path("number") number: Int): Call<Surah>
}