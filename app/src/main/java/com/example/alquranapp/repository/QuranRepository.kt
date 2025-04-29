package com.example.alquranapp.repository

import com.example.alquranapp.model.AllSurahResponse
import com.example.alquranapp.network.AlquranApi
import retrofit2.Call

class QuranRepository(private val api: AlquranApi) {
    fun fetchAllSurah(): Call<AllSurahResponse> {
        return api.getAllSurah()
    }
}
