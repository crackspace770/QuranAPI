package com.fajar.quranapi.ui.detail

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.quranapi.databinding.ActivityDetailBinding
import com.fajar.quranapi.ui.adapter.VerseAdapter
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        // Get surahNum from intent or wherever it's coming from
        val surahNum = intent.getIntExtra("surahNum", 0)

        // Observe the LiveData for Ayah details
        viewModel.ayahDetail.observe(this) { ayahDetail ->
            // Populate UI components with data from the AyahDetail
            binding.tvSurahEn.text = ayahDetail?.englishName
            binding.tvRevelation.text = ayahDetail?.revelationType
            binding.tvVerseNumber.text = "${ayahDetail?.numberOfAyahs} verses"
            binding.tvSurahNameArab.text = ayahDetail?.name

            // Set up RecyclerView for VerseItems
            val verseAdapter = VerseAdapter()
            binding.rvVerses.layoutManager = LinearLayoutManager(this)
            binding.rvVerses.adapter = verseAdapter

            // Set the verse items directly in the adapter
            ayahDetail?.ayahs?.let { verseAdapter.setVerseItems(it) }
        }

        // Observe isLoading to show/hide ProgressBar
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observe snackbarText to display error messages if needed
        viewModel.snackbarText.observe(this) { message ->
            // Handle error messages here, e.g., show a Snackbar
            Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
        }

        // Fetch Ayah detail data
        viewModel.fetchAyahDetail(surahNum)
    }

    companion object {
        const val EXTRA_AYAH = "extra_ayah"

    }
}