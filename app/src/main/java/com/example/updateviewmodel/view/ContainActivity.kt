package com.example.updateviewmodel.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.updateviewmodel.R
import com.example.updateviewmodel.model.SongSearch

class ContainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contain)
//        addSongFragment()
        openPageSongFragment()
    }

    private fun addSongFragment() {
        //fragmentManager quan ly cac fragment
        val fragmentManager = supportFragmentManager
        //quan ly phien lam viec cua transaction
        val tran = fragmentManager.beginTransaction()
        tran.replace(R.id.content, SongFragment(), SongFragment::class.java.name)
        //dong transaction
//        tran.addToBackStack(null)
        tran.commit()
    }

    fun openDetailSong(songSearch: SongSearch) {
        //fragmentManager quan ly cac fragment
        val fragmentManager = supportFragmentManager
        //quan ly phien lam viec cua transaction
        val tran = fragmentManager.beginTransaction()
        val fr = DetailSongFragment()
        fr.setItemSong(songSearch)
//        tran.replace(R.id.content, fr, DetailSongFragment::class.java.name)

        tran.setCustomAnimations(
            R.anim.open_book_right_to_left,
            R.anim.exit_book_right_to_left,
            R.anim.open_book_left_to_right,
            R.anim.exit_book_left_to_right

        )

//        tran.remove(
//            supportFragmentManager.findFragmentByTag(
//                SongFragment::class.java.name
//            )!!
//        )
        tran.hide(
            supportFragmentManager.findFragmentByTag(
                SongFragment::class.java.name
            )!!
        )
        tran.add(R.id.content, fr, DetailSongFragment::class.java.name)

        //dong transaction
        tran.addToBackStack(null)
        tran.commit()
    }

    fun addTestFragment() {
        //fragmentManager quan ly cac fragment
        val fragmentManager = supportFragmentManager
        //quan ly phien lam viec cua transaction
        val tran = fragmentManager.beginTransaction()

        tran.setCustomAnimations(
            R.anim.open_book_right_to_left,
            R.anim.exit_book_right_to_left,
            R.anim.open_book_left_to_right,
            R.anim.exit_book_left_to_right

        )

        val fr = TestFragment()
//        tran.replace(R.id.content, fr, TestFragment::class.java.name)
        tran.hide(
            supportFragmentManager.findFragmentByTag(
                DetailSongFragment::class.java.name
            )!!
        )
        tran.add(R.id.content, fr, TestFragment::class.java.name)
        //dong transaction
        tran.addToBackStack(null)
        tran.commit()
    }

    fun openPageSongFragment(){
        val fragmentManager = supportFragmentManager
        val tran = fragmentManager.beginTransaction()
        tran.add(R.id.content, PageSongFragment(), PageSongFragment::class.java.name)
        tran.commit()
    }
}