package com.example.alquranapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alquranapp.api.RetrofitClient
import com.example.alquranapp.model.Ayah
import com.example.alquranapp.model.AllSurahResponse // <--- Ganti ini sesuai model detail surahmu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun SurahDetailScreen(surahNumber: Int) {
    var surahResponse by remember { mutableStateOf<AllSurahResponse?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(surahNumber) {
        try {
            isLoading = true
            val response = withContext(Dispatchers.IO) {
                RetrofitClient.instance.getSurah(surahNumber).execute()
            }
            isLoading = false
            if (response.isSuccessful) {
                surahResponse = response.body()
            } else {
                errorMessage = "Gagal memuat surah."
            }
        } catch (e: Exception) {
            isLoading = false
            errorMessage = "Error: ${e.message}"
        }
    }

    when {
        isLoading -> {
            CircularProgressIndicator()
        }

        errorMessage.isNotEmpty() -> {
            Text(text = errorMessage, color = MaterialTheme.colors.error)
        }

        surahResponse != null -> {
            val surah = surahResponse!!.data.find { it.number == surahNumber }
            if (surah != null) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "${surah.englishName} (${surah.name})",
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyColumn {
                        items(surah.ayahs) { ayah: Ayah ->
                            AyahItem(ayah)
                        }
                    }
                }
            } else {
                Text(text = "Surah tidak ditemukan.")
            }
        }
    }
}