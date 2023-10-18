package com.fajar.quranapi.ui.quran.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SurahPagerAdapter(fragment: DetailActivity) : FragmentStateAdapter(fragment) {

    private val surahCount = 114

    override fun getItemCount(): Int {
        return surahCount
    }

    override fun createFragment(position: Int): Fragment {
        return SurahDetailFragment.newInstance(position + 1)
    }
}