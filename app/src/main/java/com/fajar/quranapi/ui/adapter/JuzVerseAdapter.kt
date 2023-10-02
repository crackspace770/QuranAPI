package com.fajar.quranapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.R
import com.fajar.quranapi.core.response.AyahItem
import com.fajar.quranapi.databinding.JuzItemBinding
import com.fajar.quranapi.databinding.JuzVerseItemBinding


class JuzVerseAdapter : RecyclerView.Adapter<JuzVerseAdapter.VerseViewHolder>() {

    private var verseItems: List<AyahItem> = emptyList()

    fun setVerseItems(items: List<AyahItem>) {
        verseItems = items
        notifyDataSetChanged()
    }

    inner class VerseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = JuzVerseItemBinding.bind(itemView)

        fun bind(verseItem: AyahItem) {
            with(binding) {
                tvJuzVerseNumber.text = verseItem.number.toString() ?: ""
                tvJuzVerses.text = verseItem.text ?: ""

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.juz_verse_item, parent, false)
        return VerseViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerseViewHolder, position: Int) {
        val verseItem = verseItems[position]
        holder.bind(verseItem)
    }

    override fun getItemCount(): Int {
        return verseItems.size
    }

}