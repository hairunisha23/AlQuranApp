package com.example.alquranapp.model

data class SurahDetailResponse(
    val data: SurahDetail
)

data class SurahDetail(
    val number: Int,
    val name: String,
    val englishName: String,
    val englishNameTranslation: String,
    val revelationType: String,
    val numberOfAyahs: Int,
    val ayahs: List<Ayah>
)
