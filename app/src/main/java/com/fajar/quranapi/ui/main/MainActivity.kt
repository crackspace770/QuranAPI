package com.fajar.quranapi.ui.main


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fajar.quranapi.R
import com.fajar.quranapi.core.adapter.SectionPagerAdapter
import com.fajar.quranapi.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity:AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewPager2
        val viewPager: ViewPager2 = binding.viewPager

        // Initialize TabLayout
        val tabLayout: TabLayout = binding.tabs

        // Create the SectionPagerAdapter and set it to the ViewPager2
        val sectionPagerAdapter = SectionPagerAdapter(this)
        viewPager.adapter = sectionPagerAdapter

        // Link the TabLayout to the ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Surah"
                1 -> tab.text = "Bookmark"
               // 2 -> tab.text = "Prayer"
            }
        }.attach()
    }




}