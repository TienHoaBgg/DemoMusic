package com.example.updateviewmodel.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter

class SongPageAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        val fr = SongFragment()
        when (position) {
            0 -> fr.firstSearch = ""
            1 -> fr.firstSearch = "Page 1"
            2 -> fr.firstSearch = "Page 2"
            3 -> fr.firstSearch = "Page 3"
        }
        return fr
    }

    override fun getCount(): Int {
        return 4
    }

    //hien thi ten o tren tablayout
    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Default"
            1 -> return "Page 1"
            2 -> return "Page 2"
            3 -> return "Page 3"
        }
        return ""
    }
}