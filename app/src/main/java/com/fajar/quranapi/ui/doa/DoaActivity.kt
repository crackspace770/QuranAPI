package com.fajar.quranapi.ui.doa

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.quranapi.core.adapter.DoaAdapter
import com.fajar.quranapi.databinding.ActivityDoaBinding


class DoaActivity:AppCompatActivity() {

    private lateinit var binding: ActivityDoaBinding
    private val viewModel: DoaViewModel by viewModels()
    private lateinit var doaAdapter: DoaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doaAdapter = DoaAdapter()

        val layoutManager = LinearLayoutManager(this)
        binding.rvDoa.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvDoa.addItemDecoration(itemDecoration)
        binding.rvDoa.adapter = doaAdapter

        viewModel.listDoa.observe(this) { doaList->
            doaAdapter.submitList(doaList)
        }

        viewModel.isLoading.observe(this){ isLoading->
            showLoading(isLoading)
        }

        doaAdapter.onItemClick = { doa->
            val moveToDetail = Intent(this, DoaDetailActivity::class.java)
            moveToDetail.putExtra(DoaDetailActivity.EXTRA_DOA_DETAIL, doa)
            startActivity(moveToDetail)
        }

        binding.searchViewDoa.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Retrieve the surahList from the mainViewModel
                val surahList = viewModel.listDoa.value.orEmpty()

                // Filter the surahList based on the search query
                val filteredList = surahList.filter { surah ->
                    surah.doa.contains(newText.orEmpty(), ignoreCase = true)
                }
                doaAdapter.submitList(filteredList)
                return true
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}