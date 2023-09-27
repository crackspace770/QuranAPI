package com.fajar.quranapi.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fajar.quranapi.ui.quran.QuranFragment
import com.fajar.quranapi.ui.quran.juz.JuzFragment
import com.fajar.quranapi.ui.quran.surah.SurahFragment

class SectionPagerAdapter(activity: QuranFragment) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = SurahFragment()
            1 -> fragment = JuzFragment()
        }
        return fragment as Fragment
    }
}