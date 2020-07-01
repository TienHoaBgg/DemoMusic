package com.example.updateviewmodel.view

import android.media.MediaPlayer
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.updateviewmodel.MusicManager
import com.example.updateviewmodel.databinding.FragmentSongBinding
import com.example.updateviewmodel.model.LinkMusic
import com.example.updateviewmodel.model.SongSearch
import com.example.updateviewmodel.viewmodel.SongViewModel
import io.reactivex.disposables.Disposable
import java.text.SimpleDateFormat

class SongFragment : Fragment(), TextWatcher, SongAdapter.CallBack {
    companion object {
        val TAG = "Test"
    }

    private lateinit var model: SongViewModel
    private var mDispose: Disposable? = null
    private lateinit var binding: FragmentSongBinding
    private lateinit var mpManager: MusicManager
    var firstSearch = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView.............")
        binding = FragmentSongBinding.inflate(inflater, container, false)
        inits()
        return binding.root
    }

    private fun inits() {
        model = SongViewModel()
        mpManager = MusicManager()
        binding.rcSong.apply {
            layoutManager = LinearLayoutManager(this@SongFragment.context)
            adapter =
                SongAdapter(this@SongFragment)
        }
        binding.data = model
        binding.lifecycleOwner = this

        binding.btnSearch.setOnClickListener {
            mDispose?.dispose()
            mDispose = model.searchSong(binding.edtSearch.text.toString().trim())
        }
        binding.edtSearch.addTextChangedListener(this)

        mDispose = model.searchSong(firstSearch)

        model.songs.observe(this, object : Observer<MutableList<SongSearch>> {
            override fun onChanged(t: MutableList<SongSearch>?) {
                (binding.rcSong.adapter as SongAdapter).notifyDataSetChanged()
            }
        })
        model.finishGetLink.observe(this,
            Observer<SongSearch> {
                binding.txtNameSong.text = it.songName
                playSong(it)
            })
    }

    private fun playSong(item:SongSearch){
        mpManager.setData(this@SongFragment.context!!,item.linkMusic!!)
        val totalTime = SimpleDateFormat("mm:ss").format(mpManager.getDuration())
        binding.txtTotaltimesong.text = totalTime
        mainThread().execute()
    }

    inner class mainThread:AsyncTask<Void,String,Int>(){
        override fun doInBackground(vararg params: Void?): Int {
            while (mpManager.getCurrentTimePlay() < mpManager.getDuration()){
                val currentTime = SimpleDateFormat("mm:ss").format(mpManager.getCurrentTimePlay())
                publishProgress(currentTime)
            }
            return 0
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            binding.txtCurrentTimeSong.text = values[0]
        }

    }



    override fun afterTextChanged(text: Editable) {
        mDispose?.dispose()
        mDispose = model.searchSong(binding.edtSearch.text.toString().trim())
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView.............")
        mDispose?.dispose()
        super.onDestroyView()
    }

    override fun onClickItem(position: Int) {
        model.getLinkSong(model.songs.value!![position].linkSong!!)
    }

    override fun getModel() = model

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.d("SongFragment", "onHiddenChanged....")
    }
}