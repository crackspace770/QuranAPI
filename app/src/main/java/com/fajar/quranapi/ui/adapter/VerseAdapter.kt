package com.fajar.quranapi.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.R
import com.fajar.quranapi.core.response.AyahsItem
import com.fajar.quranapi.databinding.VerseItemBinding

class VerseAdapter : RecyclerView.Adapter<VerseAdapter.VerseViewHolder>() {

    private var verseItems: List<AyahsItem> = emptyList()

    fun setVerseItems(items: List<AyahsItem>) {
        verseItems = items
        notifyDataSetChanged()
    }

    inner class VerseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = VerseItemBinding.bind(itemView)

        fun bind(verseItem: AyahsItem) {
            with(binding) {
                tvNumber.text = verseItem.verseNumber?.toString() ?: ""
                tvVerse.text = verseItem.text ?: ""
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.verse_item, parent, false)
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