package com.fajar.quranapi.core.ui

import androidx.recyclerview.widget.DiffUtil
import com.fajar.quranapi.core.domain.model.Surah

class SurahDiffUtil (
    private val oldList: List<Surah>,
    private val newList: List<Surah>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].number == newList[newItemPosition].number
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}