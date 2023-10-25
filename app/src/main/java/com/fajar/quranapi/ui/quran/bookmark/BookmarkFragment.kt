package com.fajar.quranapi.ui.quran.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.quranapi.core.adapter.BookmarkAdapter
import com.fajar.quranapi.core.adapter.VerseAdapter
import com.fajar.quranapi.core.data.local.entity.AyahItem
import com.fajar.quranapi.core.data.local.entity.VerseEntity
import com.fajar.quranapi.core.data.remote.response.AyahsItem
import com.fajar.quranapi.core.preference.SharedPreferencesHelper
import com.fajar.quranapi.core.ui.ViewModelFactories
import com.fajar.quranapi.databinding.FragmentBookmarkBinding
import com.fajar.quranapi.databinding.FragmentJuzBinding

class BookmarkFragment: Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private lateinit var verseAdapter: BookmarkAdapter
    private lateinit var viewModel: BookmarkViewModel
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        binding.rvBookmark.layoutManager = LinearLayoutManager(context)
        verseAdapter = BookmarkAdapter()
        binding.rvBookmark.adapter = verseAdapter
        return binding.root
    }

    private fun obtainViewModel(activity: AppCompatActivity): BookmarkViewModel {
        val factory = ViewModelFactories.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[BookmarkViewModel::class.java]
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = obtainViewModel(context as AppCompatActivity)

        // Observe the LiveData for bookmarked verses and populate the adapter
        viewModel.getBookmarkedVerses().observe(viewLifecycleOwner) { bookmarkedVerses ->
            verseAdapter.setVerseItems(bookmarkedVerses)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}