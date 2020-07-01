package com.example.updateviewmodel.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.updateviewmodel.viewmodel.SongViewModel

class MusicService :Service(){
    private lateinit var model:SongViewModel
    override fun onCreate() {
        super.onCreate()
        model = SongViewModel()
    }


    override fun onBind(intent: Intent?): IBinder? {


    return null
    }

}