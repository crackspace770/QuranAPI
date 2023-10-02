package com.fajar.quranapi.ui.quran.juz

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.quranapi.core.response.JuzItem
import com.fajar.quranapi.databinding.ActivityDetailJuzBinding
import com.fajar.quranapi.ui.adapter.JuzVerseAdapter


class JuzDetailActivity:AppCompatActivity() {

    private lateinit var binding:ActivityDetailJuzBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailJuzBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val juz = intent.getParcelableExtra<JuzItem>(EXTRA_JUZ_DETAIL)



            if (juz != null) {
                binding.tvNumberJuz.text = "Juz ${juz.number.toString()}"

                val verseAdapter = JuzVerseAdapter()
                binding.rvVerseJuz.layoutManager = LinearLayoutManager(this)
                binding.rvVerseJuz.adapter = verseAdapter
                juz.ayahs.let { verseAdapter.setVerseItems(it) }

            }

    }

    companion object{
        const val EXTRA_JUZ_DETAIL = "juz_detail"
    }

}