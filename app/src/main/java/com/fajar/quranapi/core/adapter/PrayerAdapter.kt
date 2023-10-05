package com.fajar.quranapi.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.R
import com.fajar.quranapi.core.domain.model.Prayer
import com.fajar.quranapi.databinding.PrayerItemBinding

class PrayerAdapter(private val listPrayer: ArrayList<Prayer>) : RecyclerView.Adapter<PrayerAdapter.PrayerViewHolder>() {

    private var verseItems: List<Prayer> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setVerseItems(items: List<Prayer>) {
        verseItems = items
        notifyDataSetChanged()
    }

    inner class PrayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = PrayerItemBinding.bind(itemView)

        fun bind(verseItem: Prayer) {
            with(binding) {
                tvPrayer.text = verseItem.prayerName
                tvPrayerTime.text = verseItem.prayerTime

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrayerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.prayer_item, parent, false)
        return PrayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrayerViewHolder, position: Int) {
        val verseItem = listPrayer[position]
        holder.bind(verseItem)
    }

    override fun getItemCount(): Int {
        return listPrayer.size
    }

}