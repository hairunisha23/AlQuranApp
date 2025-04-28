package com.example.alquranapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.alquranapp.api.RetrofitClient
import com.example.alquranapp.model.Surah
import com.example.alquranapp.model.AllSurahResponse
import com.example.alquranapp.ui.theme.AlQuranAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlQuranAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    QuranApp()
                }
            }
        }
    }
}

@Composable
fun QuranApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "surah_list") {
        composable("surah_list") {
            SurahListScreen(navController)
        }
        composable(
            route = "surah_detail/{surahNumber}",
            arguments = listOf(navArgument("surahNumber") { type = NavType.IntType })
        ) { backStackEntry ->
            val surahNumber = backStackEntry.arguments?.getInt("surahNumber") ?: 1
            SurahDetailScreen(surahNumber)
        }
    }
}

@Composable
fun SurahListScreen() {
    var surahList by remember { mutableStateOf<List<Surah>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        RetrofitClient.instance.getAllSurah().enqueue(object : Callback<AllSurahResponse> {
            override fun onResponse(
                call: Call<AllSurahResponse>,
                response: Response<AllSurahResponse>
            ) {
                isLoading = false
                if (response.isSuccessful) {
                    surahList = response.body()?.data ?: emptyList()  // ✅ Update surahList
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

    // 🔥 Tambahkan tampilan
    when {
        isLoading -> {
            Text(text = "Loading...")
        }
        errorMessage.isNotEmpty() -> {
            Text(text = errorMessage)
        }
        else -> {
            LazyColumn {
                items(surahList) { surah ->
                    Text(text = surah.englishName)
                }
            }
        }
    }
}

@Composable
fun SurahItem(surah: Surah) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${surah.number}. ${surah.englishName} (${surah.name})", style = MaterialTheme.typography.h6)
            Text(text = "Jumlah ayat: ${surah.numberOfAyahs}", style = MaterialTheme.typography.body2)
        }
    }
}
