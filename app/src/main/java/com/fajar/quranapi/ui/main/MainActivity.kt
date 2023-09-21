package com.fajar.quranapi.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.quranapi.ui.adapter.SurahAdapter
import com.fajar.quranapi.databinding.ActivityMainBinding
import com.fajar.quranapi.core.response.SurahResponse
import com.fajar.quranapi.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]

        val layoutManager = LinearLayoutManager(this)
        binding.rvSurah.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvSurah.addItemDecoration(itemDecoration)

        mainViewModel.listSurah.observe(this) { surahList ->
            setSurahData(surahList)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }


    }

    private fun setSurahData(surahList: ArrayList<SurahResponse>) {

        val adapter = SurahAdapter()
        adapter.submitList(surahList)
        binding.rvSurah.adapter = adapter

        adapter.onItemClick = { surah ->
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra("surahNum", surah.number) // Pass the surah number
            startActivity(intent)
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}