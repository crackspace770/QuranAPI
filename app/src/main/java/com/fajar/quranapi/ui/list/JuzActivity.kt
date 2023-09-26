package com.fajar.quranapi.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.R
import com.fajar.quranapi.core.response.JuzResponse
import com.fajar.quranapi.ui.adapter.SurahAdapter
import com.fajar.quranapi.core.response.SurahResponse
import com.fajar.quranapi.databinding.ActivityJuzBinding
import com.fajar.quranapi.ui.adapter.JuzAdapter
import com.fajar.quranapi.ui.detail.DetailActivity
import com.fajar.quranapi.ui.main.MainViewModel
import com.google.android.material.snackbar.Snackbar

class JuzActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJuzBinding
    private lateinit var juzAdapter: JuzAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJuzBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        val viewModel = ViewModelProvider(this).get(JuzViewModel::class.java)

        // Initialize RecyclerView and its Adapter
        juzAdapter = JuzAdapter { selectedJuzItem ->
            // Handle item click here, e.g., navigate to the verses of the selected Juz.
            // You can pass the selected JuzItem to the next activity/fragment for displaying its verses.
        }

        binding.rvJuz.layoutManager = LinearLayoutManager(this)
        binding.rvJuz.adapter = juzAdapter

        // Observe the list of Juz from the ViewModel
        viewModel.juzList.observe(this) { juzList ->
            juzAdapter.submitList(juzList)
        }
    }
}