package com.fajar.quranapi.ui.quran.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fajar.quranapi.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up ViewPager2 and TabLayout to navigate through surahs
        val pagerAdapter = SurahPagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter

        // Get surahNum from intent or wherever it's coming from
        val surahNum = intent.getIntExtra("surahNum", 0)

        // Set the current item to the selected surah number
        binding.viewPager.currentItem = surahNum - 1

        // Initialize TabLayout with Surah names
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = "Surah ${position + 1}"
        }.attach()
    }

    companion object {
        const val EXTRA_AYAH = "extra_ayah"
    }
}