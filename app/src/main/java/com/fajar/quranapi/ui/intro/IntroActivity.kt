package com.fajar.quranapi.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fajar.quranapi.databinding.ActivityIntroBinding
import com.fajar.quranapi.ui.doa.DoaActivity
import com.fajar.quranapi.ui.main.MainActivity
import com.fajar.quranapi.ui.prayer.PrayerActivity
import com.fajar.quranapi.ui.prayer.PrayerFragment

class IntroActivity:AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBacaQuran.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnBacaDoa.setOnClickListener {
            startActivity(Intent(this, DoaActivity::class.java))
        }

        binding.btnJadwalShalat.setOnClickListener {
            startActivity(Intent(this, PrayerActivity::class.java))
        }
    }

}