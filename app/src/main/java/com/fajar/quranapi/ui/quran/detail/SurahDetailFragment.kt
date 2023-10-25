package com.fajar.quranapi.ui.quran.detail

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.core.adapter.VerseAdapter
import com.fajar.quranapi.core.preference.SharedPreferencesHelper
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

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel {
        val factory = DetailViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[ DetailViewModel::class.java]
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = obtainViewModel(context as AppCompatActivity)
        viewModel = ViewModelProvider(this, DetailViewModelFactory.getInstance(requireActivity().application))[DetailViewModel::class.java]

        val verseAdapter = VerseAdapter(viewModel)

        // Get the Surah number from the arguments
        val surahNum = arguments?.getInt("surahNum", 1) ?: 1

        // Observe the LiveData for Ayah details and populate UI components
        viewModel.ayahDetail.observe(viewLifecycleOwner) { ayahDetail ->
            binding.tvSurahEn.text = ayahDetail?.translation
            binding.tvRevelation.text = ayahDetail?.revelation
            binding.tvVerseNumber.text = "${ayahDetail?.numberOfAyahs} verses"
            binding.tvSurahNameArab.text = ayahDetail?.name

            binding.rvVerses.layoutManager = LinearLayoutManager(requireContext())
            binding.rvVerses.adapter = verseAdapter
            // Set the verse items directly in the adapter
            ayahDetail?.ayahs?.let { verseAdapter.setVerseItems(it) }

            val layoutManager = LinearLayoutManager(requireContext())
            binding.rvVerses.layoutManager = layoutManager


            binding.rvVerses.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    // Get the first visible item position
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    // Set the highlighted position in the adapter
                    verseAdapter.setHighlightedPosition(firstVisibleItemPosition)
                }
            })

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