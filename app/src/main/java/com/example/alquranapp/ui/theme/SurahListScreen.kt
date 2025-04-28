package com.example.alquranapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.alquranapp.api.RetrofitClient
import com.example.alquranapp.model.AllSurahResponse
import com.example.alquranapp.model.Surah
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SurahListScreen(navController: NavController) {
    var surahList by remember { mutableStateOf<List<Surah>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        RetrofitClient.instance.getAllSurah().enqueue(object : Callback<AllSurahResponse> {
            override fun onResponse(call: Call<AllSurahResponse>, response: Response<AllSurahResponse>) {
                isLoading = false
                if (response.isSuccessful) {
                    surahList = response.body()?.data ?: emptyList()
                } else {
                    errorMessage = "Gagal memuat daftar surah."
                }
            }

            override fun onFailure(call: Call<AllSurahResponse>, t: Throwable) {
                isLoading = false
                errorMessage = "Error: ${t.message}"
            }
        })
    }

    when {
        isLoading -> {
            CircularProgressIndicator()
        }
        errorMessage.isNotEmpty() -> {
            Text(text = errorMessage, color = MaterialTheme.colors.error)
        }
        else -> {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(surahList) { surah ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                navController.navigate("surah_detail/${surah.number}")
                            },
                        elevation = 4.dp
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "${surah.number}. ${surah.englishName} (${surah.name})", style = MaterialTheme.typography.h6)
                            Text(text = "Jumlah ayat: ${surah.numberOfAyahs}", style = MaterialTheme.typography.body2)
                        }
                    }
                }
            }
        }
    }
}
