package com.fajar.quranapi.core.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fajar.quranapi.ui.doa.DoaFragment
import com.fajar.quranapi.ui.prayer.PrayerFragment
import com.fajar.quranapi.ui.quran.bookmark.BookmarkFragment
import com.fajar.quranapi.ui.quran.surah.SurahFragment

class SectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = SurahFragment()
            1 -> fragment = DoaFragment()
            2 -> fragment = PrayerFragment()
        }
        return fragment as Fragment
    }
}