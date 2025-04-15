package com.example.alquranapp.ui

import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.alquranapp.api.RetrofitClient
import com.example.alquranapp.model.Surah
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        getSurah(114) // Mengambil Surah An-Naas
    }

    private fun getSurah(number: Int) {
        RetrofitClient.instance.getSurah(number).enqueue(object : Callback<Surah> {
            override fun onResponse(call: Call<Surah>, response: Response<Surah>) {
                if (response.isSuccessful) {
                    val surah = response.body()
                    surah?.let {
                        displaySurah(it)
                    }
                }
            }

            override fun onFailure(call: Call<Surah>, t: Throwable) {
                textView.text = "Error: ${t.message}"
            }
        })
    }

    private fun displaySurah(surah: Surah) {
        val sb = StringBuilder()
        sb.append("Surah: ${surah.name}\n")
        sb.append("Ayahs:\n")
        for (ayah in surah.ayahs) {
            sb.append("${ayah.number}: ${ayah.text}\n")
        }
        textView.text = sb.toString()
    }
}