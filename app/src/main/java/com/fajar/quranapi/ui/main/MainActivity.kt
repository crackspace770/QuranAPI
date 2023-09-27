package com.fajar.quranapi.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fajar.quranapi.R
import com.fajar.quranapi.databinding.ActivityMainBinding
import com.fajar.quranapi.ui.quran.QuranFragment
import com.fajar.quranapi.ui.quran.bookmark.BookmarkFragment
import com.fajar.quranapi.ui.quran.juz.JuzFragment
import com.fajar.quranapi.ui.quran.surah.SurahFragment

class MainActivity:AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            setupBottomNavMenu(navController)
        }
    }

    private fun setupBottomNavMenu(navController: NavController) {
        setFragment(QuranFragment())
        val bottomNav = binding.navView
        bottomNav.setupWithNavController(navController)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> setFragment(QuranFragment())
                R.id.nav_bookmark -> setFragment(BookmarkFragment())
                else -> setFragment(QuranFragment())
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun FragmentManager.instantiate(className: String): Fragment {
        return fragmentFactory.instantiate(ClassLoader.getSystemClassLoader(), className)
    }

    @SuppressLint("CommitTransaction")
    private fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .commit()
    }

}