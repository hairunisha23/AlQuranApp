package com.example.alquranapp.model

data class Surah(
    val number: Int,
    val name: String,
    val englishName: String,
    val englishNameTranslation: String,
    val revelationType: String,
    val numberOfAyahs: Int,
    val ayahs: List<Ayah> = emptyList() // default kosong supaya aman saat belum load ayat
)
