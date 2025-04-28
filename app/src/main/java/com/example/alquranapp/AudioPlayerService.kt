package com.example.alquranapp

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AudioPlayerService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
