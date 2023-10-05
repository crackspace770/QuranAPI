package com.fajar.quranapi.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.R
import com.fajar.quranapi.databinding.SurahItemBinding
import com.fajar.quranapi.core.data.remote.response.SurahsResponse

class SurahAdapter : ListAdapter<SurahsResponse, SurahAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((SurahsResponse) -> Unit)? = null

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = SurahItemBinding.bind(itemView)
        fun bind(data: SurahsResponse) {
            with(binding) {
                tvSurahEn.text = data.translation
                tvRevelation.text = data.revelation
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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SurahsResponse>() {
            override fun areItemsTheSame(oldItem: SurahsResponse, newItem: SurahsResponse): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: SurahsResponse, newItem: SurahsResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}