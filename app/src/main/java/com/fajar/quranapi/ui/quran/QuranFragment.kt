package com.fajar.quranapi.ui.quran

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fajar.quranapi.R
import com.fajar.quranapi.databinding.FragmentQuranBinding
import com.fajar.quranapi.core.adapter.SectionPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class QuranFragment:Fragment(R.layout.fragment_quran) {

    private val binding: FragmentQuranBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val sectionsPagerAdapter = SectionPagerAdapter(this@QuranFragment)
            viewPager.adapter = sectionsPagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
        }
    }
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.title_surah,
            R.string.title_juz
        )
    }

}