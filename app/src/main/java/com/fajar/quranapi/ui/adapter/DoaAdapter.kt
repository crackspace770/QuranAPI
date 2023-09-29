package com.fajar.quranapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.R
import com.fajar.quranapi.core.response.DoaResponse
import com.fajar.quranapi.core.response.SurahResponse
import com.fajar.quranapi.databinding.DoaItemBinding
import java.util.ArrayList


class DoaAdapter : ListAdapter<DoaResponse, DoaAdapter.ListViewHolder>(DIFF_CALLBACK) {

    private var listData = ArrayList<DoaResponse>()
    var onItemClick: ((DoaResponse) -> Unit)? = null

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = DoaItemBinding.bind(itemView)
        fun bind(data: DoaResponse) {
            with(binding) {
                tvNamaDoa.text = data.doa
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.doa_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val doa = getItem(position)
        holder.bind(doa)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DoaResponse>() {
            override fun areItemsTheSame(oldItem: DoaResponse, newItem: DoaResponse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DoaResponse, newItem: DoaResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}