package com.fajar.quranapi.ui.prayer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.R
import com.fajar.quranapi.core.adapter.PrayerAdapter
import com.fajar.quranapi.core.domain.model.Prayer
import com.fajar.quranapi.databinding.ActivityPrayerBinding
import com.fajar.quranapi.ui.compass.CompassActivity
import com.fajar.quranapi.ui.compass.CompassQibla
import com.fajar.quranapi.ui.main.MainActivity
import com.fajar.quranapi.ui.setting.SettingActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


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

    //    binding.btnSetting.setOnClickListener {
     //       startActivity(Intent(this, SettingActivity::class.java))
      //  }

        binding.btnCompas.setOnClickListener {
            startActivity(Intent(this, CompassActivity::class.java))
        }


        updateCurrentTime()
        // Schedule a runnable to update the time every second
        handler.postDelayed(timeUpdateRunnable, 1000)


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
        return SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove the callback when the activity is destroyed to avoid memory leaks
        handler.removeCallbacks(timeUpdateRunnable)
    }

}