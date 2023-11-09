package com.fajar.quranapi.ui.prayer


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fajar.quranapi.R
import com.fajar.quranapi.databinding.ActivityPrayerBinding
import com.fajar.quranapi.ui.compass.QiblaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class PrayerActivity:AppCompatActivity(R.layout.activity_prayer) {

    private val binding: ActivityPrayerBinding by viewBinding()

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
        setFragment(PrayerFragment())
        val bottomNav = binding.navView
        bottomNav.setupWithNavController(navController)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_shalat -> setFragment(PrayerFragment())
                R.id.nav_compass -> setFragment(QiblaFragment())
                else -> setFragment(PrayerFragment())
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