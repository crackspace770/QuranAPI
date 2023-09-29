package com.fajar.quranapi.ui.doa

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.fajar.quranapi.databinding.FragmentDoaBinding
import com.fajar.quranapi.ui.adapter.DoaAdapter
import com.fajar.quranapi.ui.quran.detail.DetailActivity


class DoaFragment: Fragment() {

    private var _binding: FragmentDoaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DoaViewModel by viewModels()
    private lateinit var doaAdapter: DoaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doaAdapter = DoaAdapter()

        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvDoa.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        binding.rvDoa.addItemDecoration(itemDecoration)
        binding.rvDoa.adapter = doaAdapter

        viewModel.listDoa.observe(viewLifecycleOwner) { doaList->
            doaAdapter.submitList(doaList)
        }

        viewModel.isLoading.observe(viewLifecycleOwner){ isLoading->
            showLoading(isLoading)
        }

        doaAdapter.onItemClick = { doa->
            val moveToDetail = Intent(requireContext(), DoaDetailActivity::class.java)
            moveToDetail.putExtra(DoaDetailActivity.EXTRA_DOA_DETAIL, doa)
            startActivity(moveToDetail)
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