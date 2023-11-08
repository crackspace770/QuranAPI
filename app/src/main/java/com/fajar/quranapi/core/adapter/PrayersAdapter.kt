package com.fajar.quranapi.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.R
import com.fajar.quranapi.core.data.remote.response.DoaResponse
import com.fajar.quranapi.core.data.remote.response.Jadwal
import com.fajar.quranapi.databinding.PrayerItemBinding



class PrayersAdapter: ListAdapter<Jadwal, PrayersAdapter.ListViewHolder>(DIFF_CALLBACK) {


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = PrayerItemBinding.bind(itemView)
        fun bind(data: Jadwal) {
            with(binding) {
                tvPrayer.text = data.data.toString()
                tvPrayerTime.text = data.status
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.prayer_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val doa = getItem(position)
        holder.bind(doa)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Jadwal>() {
            override fun areItemsTheSame(oldItem: Jadwal, newItem: Jadwal): Boolean {
                return oldItem.status == newItem.status
            }

            override fun areContentsTheSame(oldItem: Jadwal, newItem: Jadwal): Boolean {
                return oldItem == newItem
            }
        }
    }

}