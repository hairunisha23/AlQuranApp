package com.example.alquranapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alquranapp.model.Ayah

@Composable
fun AyahItem(ayah: Ayah) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "${ayah.numberInSurah}. ${ayah.text}")
    }
}
