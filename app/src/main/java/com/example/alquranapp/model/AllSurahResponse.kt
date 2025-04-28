package com.example.alquranapp.model

data class AllSurahResponse(
    val code: Int,
    val status: String,
    val data: List<Surah>
)
