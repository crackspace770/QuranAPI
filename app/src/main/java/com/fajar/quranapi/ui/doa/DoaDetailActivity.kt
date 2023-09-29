package com.fajar.quranapi.ui.doa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fajar.quranapi.core.model.Doa
import com.fajar.quranapi.core.response.DoaResponse
import com.fajar.quranapi.databinding.ActivityDetailDoaBinding

class DoaDetailActivity:AppCompatActivity() {

    private lateinit var binding : ActivityDetailDoaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDoaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val doa = intent.getParcelableExtra<DoaResponse>(EXTRA_DOA_DETAIL)

        if (doa != null) {
            binding.apply {
                tvJudulDoa.text = doa.doa
                tvDoa.text = doa.ayat
                tvLatin.text = doa.latin
                tvArti.text = doa.artinya
            }
            }

    }

    companion object {
        const val EXTRA_DOA_DETAIL = "doa_detail"
    }
}