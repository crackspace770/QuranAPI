package com.fajar.quranapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.R
import com.fajar.quranapi.databinding.SurahItemBinding
import com.fajar.quranapi.core.response.SurahResponse

class SurahAdapter : ListAdapter<SurahResponse, SurahAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((SurahResponse) -> Unit)? = null

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = SurahItemBinding.bind(itemView)
        fun bind(data: SurahResponse) {
            with(binding) {
                tvSurahEN.text = data.englishName
                tvRevelation.text = data.revelationType
                tvAyah.text = data.numberOfAyahs.toString()
                tvNameArab.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.surah_item, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val surah = getItem(position)
        holder.bind(surah)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SurahResponse>() {
            override fun areItemsTheSame(oldItem: SurahResponse, newItem: SurahResponse): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SurahResponse, newItem: SurahResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}