package com.fajar.quranapi.ui.prayer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.fajar.quranapi.R
import com.fajar.quranapi.core.adapter.PrayerAdapter
import com.fajar.quranapi.core.domain.model.Prayer
import com.fajar.quranapi.databinding.ActivityPrayerBinding
import com.fajar.quranapi.ui.main.MainActivity
import com.fajar.quranapi.ui.setting.NotificationWorker
import com.fajar.quranapi.ui.setting.SettingActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class PrayerActivity:AppCompatActivity() {

    private lateinit var rvPrayer: RecyclerView
    private val list = ArrayList<Prayer>()
    private lateinit var binding:ActivityPrayerBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnQuran.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnSetting.setOnClickListener {
            startActivity(Intent(this, SettingActivity::class.java))
        }

        rvPrayer= findViewById(R.id.rvPrayer)
        rvPrayer.setHasFixedSize(true)
        list.addAll(getListPrayers())
        showRecyclerList()

        updateCurrentTime()
        // Schedule a runnable to update the time every second
        handler.postDelayed(timeUpdateRunnable, 1000)

        val prayerTimes: List<Prayer> = getListPrayers() // Get your list of prayer times


    }

    private fun getListPrayers(): ArrayList<Prayer> {
        val dataName = resources.getStringArray(R.array.prayer_name)
        val dataDescription = resources.getStringArray(R.array.prayer_time)
        val listHero = ArrayList<Prayer>()
        for (i in dataName.indices) {
            val hero = Prayer(dataName[i], dataDescription[i])
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvPrayer.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = PrayerAdapter(list)
        rvPrayer.adapter = listHeroAdapter
    }

    private val timeUpdateRunnable = object : Runnable {
        override fun run() {
            updateCurrentTime()
            // Schedule the next update after 1 second
            handler.postDelayed(this, 1000)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateCurrentTime() {
        val currentTime = getCurrentTimeIn24HourFormat()
        binding.tvCounter.text = "$currentTime WIB"
    }

    private fun getCurrentTimeIn24HourFormat(): String {
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        return currentTime
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove the callback when the activity is destroyed to avoid memory leaks
        handler.removeCallbacks(timeUpdateRunnable)
    }

}