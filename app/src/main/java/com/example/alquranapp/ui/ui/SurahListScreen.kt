package com.example.alquranapp.ui.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.alquranapp.viewmodel.QuranViewModel

@Composable
fun SurahListScreen(
    navController: NavController,
    quranViewModel: QuranViewModel
) {
    val surahList = quranViewModel.surahList.collectAsState()
    val isLoading = quranViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        quranViewModel.getAllSurah()
    }

    when {
        isLoading.value -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        else -> {
            LazyColumn(modifier = Modifier.padding(16.dp)) {
                items(surahList.value) { surah ->
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
                            Text(
                                text = "${surah.number}. ${surah.englishName} (${surah.name})",
                                style = MaterialTheme.typography.h6
                            )
                            Text(
                                text = "Jumlah ayat: ${surah.numberOfAyahs}",
                                style = MaterialTheme.typography.body2
                            )
                        }
                    }
                }
            }
        }
    }
}
