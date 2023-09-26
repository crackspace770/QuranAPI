package com.fajar.quranapi.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.core.response.JuzItem

import com.fajar.quranapi.databinding.JuzItemBinding

class JuzAdapter(private val onItemClick: (JuzItem) -> Unit) :
    RecyclerView.Adapter<JuzAdapter.ViewHolder>() {

    private val juzList: MutableList<JuzItem> = mutableListOf()

    inner class ViewHolder(private val binding: JuzItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(juzItem: JuzItem) {
            binding.tvJuz.text = "Juz ${juzItem.number}"
            binding.root.setOnClickListener {
                onItemClick.invoke(juzItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = JuzItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val juzItem = juzList[position]
        holder.bind(juzItem)
    }

    override fun getItemCount(): Int {
        return juzList.size
    }

    fun submitList(newList: List<JuzItem>) {
        juzList.clear()
        juzList.addAll(newList)
        notifyDataSetChanged()
    }
}