package com.example.alquranapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alquranapp.viewmodel.QuranViewModel
import com.example.alquranapp.ui.component.AyahItem


@Composable
fun SurahDetailScreen(
    surahNumber: Int,
    quranViewModel: QuranViewModel
) {
    val surahDetail = quranViewModel.selectedSurah.collectAsState()

    LaunchedEffect(surahNumber) {
        quranViewModel.getSurahDetail(surahNumber)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = surahDetail.value?.englishName ?: "Detail Surah") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            surahDetail.value?.let { surah ->
                Text("${surah.englishName} (${surah.name})", style = MaterialTheme.typography.h6)
                Text("Jumlah ayat: ${surah.numberOfAyahs}", style = MaterialTheme.typography.body2)
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(surah.ayahs) { ayah ->
                        AyahItem(ayah = ayah)
                    }
                }
            } ?: Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}
