package com.fajar.quranapi.ui.quran.detail

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fajar.quranapi.R

class SurahPagerAdapter(fragment: DetailActivity) : FragmentStateAdapter(fragment) {

    private val surahCount = 114

    override fun getItemCount(): Int {
        return surahCount
    }

    override fun createFragment(position: Int): Fragment {
        return SurahDetailFragment.newInstance(position + 1)
    }

    // Get the Surah name from the array and set it as the TabLayout text
    fun getSurahName(position: Int, resources: Resources): String {
        val surahNames = resources.getStringArray(R.array.surah_name)
        return surahNames.getOrNull(position) ?: "Surah ${position + 1}"
    }
}