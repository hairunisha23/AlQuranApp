package com.example.alquranapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alquranapp.model.Surah
import com.example.alquranapp.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log
import com.example.alquranapp.model.SurahDetail


class QuranViewModel : ViewModel() {
    private val _surahList = MutableStateFlow<List<Surah>>(emptyList())
    val surahList: StateFlow<List<Surah>> = _surahList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _selectedSurah = MutableStateFlow<SurahDetail?>(null)
    val selectedSurah: StateFlow<SurahDetail?> = _selectedSurah

    suspend fun getSurahDetail(number: Int) {
        try {
            // Gunakan alamat API yang benar sesuai interface
            val response = RetrofitInstance.api.getSurahDetail(number)
            // Akses properti data dari response sesuai model SurahDetailResponse
            _selectedSurah.value = response.data
        } catch (e: Exception) {
            Log.e("QuranViewModel", "Error fetching surah detail", e)
        }
    }

    fun getAllSurah() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = RetrofitInstance.api.getAllSurah().execute()
                if (response.isSuccessful) {
                    _surahList.value = response.body()?.data ?: emptyList()
                } else {
                    Log.e("QuranViewModel", "Failed: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("QuranViewModel", "Exception: ", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
