package com.fajar.quranapi.ui.quran.surah

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.quranapi.core.adapter.SurahAdapter
import com.fajar.quranapi.core.ui.ViewModelFactory
import com.fajar.quranapi.databinding.FragmentSurahBinding
import com.fajar.quranapi.ui.quran.detail.DetailActivity

class SurahFragment : Fragment() {

    private var _binding: FragmentSurahBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: SurahViewModel by viewModels()
    private lateinit var surahsViewModel: SurahsViewModel
    private val surahAdapter = SurahAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSurahBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvSurah.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvSurah.addItemDecoration(itemDecoration)
        binding.rvSurah.adapter = surahAdapter

        val factory = ViewModelFactory.getInstance(requireActivity())
        surahsViewModel = ViewModelProvider(this, factory)[SurahsViewModel::class.java]

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Retrieve the surahList from the mainViewModel
                val surahList = mainViewModel.listSurah.value.orEmpty()

                // Filter the surahList based on the search query
                val filteredList = surahList.filter { surah ->
                    surah.name.contains(newText.orEmpty(), ignoreCase = true)
                }
                surahAdapter.submitList(filteredList)
                return true
            }
        })

        mainViewModel.listSurah.observe(viewLifecycleOwner) { surahList ->
            surahAdapter.submitList(surahList)
        }

        mainViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            showLoading(isLoading)
        }

        surahAdapter.onItemClick = { surah ->
            // Handle item click here
            val intent = Intent(requireActivity(), DetailActivity::class.java)
            intent.putExtra("surahNum", surah.number) // Pass the surah number
            startActivity(intent)
        }


    }



    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}