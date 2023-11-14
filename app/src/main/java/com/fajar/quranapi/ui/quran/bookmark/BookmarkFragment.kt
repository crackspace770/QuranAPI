package com.fajar.quranapi.ui.quran.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.quranapi.core.adapter.BookmarkAdapter
import androidx.navigation.fragment.findNavController
import com.fajar.quranapi.databinding.FragmentBookmarkBinding


class BookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private lateinit var verseAdapter: BookmarkAdapter
    private lateinit var viewModel: BookmarkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        binding.rvBookmark.layoutManager = LinearLayoutManager(context)
        verseAdapter = BookmarkAdapter(requireContext())
        binding.rvBookmark.adapter = verseAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[BookmarkViewModel::class.java]

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