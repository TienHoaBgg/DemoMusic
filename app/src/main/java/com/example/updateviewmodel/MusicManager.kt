package com.example.updateviewmodel

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri

class MusicManager : MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private var media:MediaPlayer? = null

    fun setData(context:Context,url:String){
        media?.release()
        media = MediaPlayer()
        media!!.setDataSource(context, Uri.parse(url))
        media?.setOnErrorListener(this)
        media?.setOnPreparedListener(this)
        media?.prepareAsync()

    }

    fun start():Boolean{
        if (media == null){
            return false
        }
        media?.start()
        return true
    }
    fun stop():Boolean{
        if (media == null){
            return false
        }
        media?.stop()
        return true

    }
    fun pause():Boolean{
        if (media == null){
            return false
        }
        media?.pause()
        return true

    }
    fun release(){
        media?.release()
    }
    fun isLoop(loop:Boolean){
        media?.isLooping = loop
    }
    fun getDuration():Int{
        return  media!!.duration
    }
    fun getCurrentTimePlay():Int{
        return media!!.currentPosition
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        return true
    }

    override fun onPrepared(mp: MediaPlayer?) {
        start()
    }

}