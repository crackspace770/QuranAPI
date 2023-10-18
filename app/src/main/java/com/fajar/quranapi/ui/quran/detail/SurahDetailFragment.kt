package com.fajar.quranapi.ui.quran.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.quranapi.core.adapter.VerseAdapter
import com.fajar.quranapi.databinding.ActivityDetailBinding
import com.fajar.quranapi.databinding.FragmentPagerSurahBinding
import com.fajar.quranapi.databinding.FragmentSurahDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class SurahDetailFragment : Fragment() {
    private lateinit var binding: FragmentSurahDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSurahDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        // Get the Surah number from the arguments
        val surahNum = arguments?.getInt("surahNum", 1) ?: 1

        // Observe the LiveData for Ayah details and populate UI components
        viewModel.ayahDetail.observe(viewLifecycleOwner) { ayahDetail ->
            binding.tvSurahEn.text = ayahDetail?.translation
            binding.tvRevelation.text = ayahDetail?.revelation
            binding.tvVerseNumber.text = "${ayahDetail?.numberOfAyahs} verses"
            binding.tvSurahNameArab.text = ayahDetail?.name

            // Set up RecyclerView for VerseItems
            val verseAdapter = VerseAdapter()
            binding.rvVerses.layoutManager = LinearLayoutManager(requireContext())
            binding.rvVerses.adapter = verseAdapter

            // Set the verse items directly in the adapter
            ayahDetail?.ayahs?.let { verseAdapter.setVerseItems(it) }
        }

        // Fetch Ayah detail data
        viewModel.fetchSurahDetail(surahNum)
    }

    companion object {
        fun newInstance(surahNum: Int): SurahDetailFragment {
            val fragment = SurahDetailFragment()
            val args = Bundle()
            args.putInt("surahNum", surahNum)
            fragment.arguments = args
            return fragment
        }
    }
}