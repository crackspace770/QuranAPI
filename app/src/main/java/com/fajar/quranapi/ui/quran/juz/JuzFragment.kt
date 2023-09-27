package com.fajar.quranapi.ui.quran.juz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.quranapi.databinding.FragmentJuzBinding
import com.fajar.quranapi.ui.adapter.JuzAdapter

class JuzFragment : Fragment() {

    private var _binding: FragmentJuzBinding? = null
    private val binding get() = _binding!!

    private val viewModel: JuzViewModel by viewModels()
    private lateinit var juzAdapter: JuzAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJuzBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        juzAdapter = JuzAdapter { selectedJuzItem ->
            // Handle item click here, e.g., navigate to the verses of the selected Juz.
            // You can pass the selected JuzItem to the next activity/fragment for displaying its verses.
        }

        binding.rvJuz.layoutManager = LinearLayoutManager(requireContext())
        binding.rvJuz.adapter = juzAdapter

        viewModel.juzList.observe(viewLifecycleOwner) { juzList ->
            juzAdapter.submitList(juzList)
        }

        viewModel.isLoading.observe(viewLifecycleOwner){isLoading->
            showLoading(isLoading)
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