package com.example.alquranapp.model

data class Ayah(
    val number: Int,
    val text: String,
    val juz: Int,
    val manzil: Int,
    val page: Int,
    val ruku: Int,
    val hizbQuarter: Int,
    val sajda: Boolean
)
