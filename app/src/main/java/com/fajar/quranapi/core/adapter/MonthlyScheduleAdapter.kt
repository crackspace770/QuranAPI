package com.fajar.quranapi.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fajar.quranapi.core.data.remote.response.MonthlyScheduleResponse
import com.fajar.quranapi.databinding.ItemScheduleMonthlyBinding

class MonthlyScheduleAdapter : RecyclerView.Adapter<MonthlyScheduleAdapter.ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ItemScheduleMonthlyBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.bind(item = differ.currentList[position], position = position)

    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ScheduleViewHolder(private val binding: ItemScheduleMonthlyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MonthlyScheduleResponse.Data.Jadwal, position: Int) {
            binding.run {
                tvBoxImsakClock.text = item.imsak
                tvBoxDzuhurClock.text = item.dzuhur
                tvBoxAsharClock.text = item.ashar
                tvBoxMaghribClock.text = item.maghrib
                tvBoxIsyaClock.text = item.isya
                tvDateBox.text = item.tanggal
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<MonthlyScheduleResponse.Data.Jadwal>() {
        override fun areItemsTheSame(
            oldExampleModel: MonthlyScheduleResponse.Data.Jadwal, newExampleModel: MonthlyScheduleResponse.Data.Jadwal
        ): Boolean {
            return oldExampleModel.date == newExampleModel.date
        }

        override fun areContentsTheSame(
            oldExampleModel: MonthlyScheduleResponse.Data.Jadwal, newExampleModel: MonthlyScheduleResponse.Data.Jadwal
        ): Boolean {
            return oldExampleModel == newExampleModel
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

}