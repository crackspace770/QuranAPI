package com.fajar.quranapi.ui.shalat.prayer

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.fajar.quranapi.R
import com.fajar.quranapi.core.adapter.MonthlyScheduleAdapter
import com.fajar.quranapi.core.data.local.entity.MonthlyModel
import com.fajar.quranapi.core.data.remote.network.ApiService
import com.fajar.quranapi.core.data.remote.network.RetrofitClient
import com.fajar.quranapi.core.utils.Const.month
import com.fajar.quranapi.databinding.FragmentPrayerMonthlyBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class MonthlyScheduleFragment: Fragment(R.layout.fragment_prayer_monthly) {

    private var _binding: FragmentPrayerMonthlyBinding? = null
    private val binding get() = _binding!!

    private val monthlyAdapter = MonthlyScheduleAdapter()
    private var monthlyList = ArrayList<MonthlyModel>()

    var currentIndex = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPrayerMonthlyBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvScheduleMonth.adapter = monthlyAdapter

        val date = getCurrentDateTime()
        val currentYear = date.toString("yyyy")
        val currentMonth = date.toString("MM")

        getMonthlySchedule(currentMonth, currentYear)

        month.map {
            if(currentMonth == it.id) {
                currentIndex = it.index
                binding.tvMonth.text = "${it.name} $currentYear"
            }
        }

        binding.ibLeft.setOnClickListener {
            if(currentIndex != 1) {
                currentIndex -= 1
                Log.e("test", "test current index $currentIndex")
                month.map {
                    if(currentIndex == it.index) {
                        binding.tvMonth.text = "${it.name} $currentYear"
                        getMonthlySchedule(it.id, currentYear)
                    }
                }
            }

        }
        binding.ibRight.setOnClickListener {
            if(currentIndex != 12) {
                currentIndex += 1
                month.map {
                    if(currentIndex == it.index) {
                        binding.tvMonth.text = "${it.name} $currentYear"
                        getMonthlySchedule(it.id, currentYear)
                    }
                }
            }
        }

    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    private fun getMonthlySchedule(month: String, year: String) {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiService::class.java)
        lifecycleScope.launch {
            try {
                val response = apiInterface.getScheduleMonthly("1219", year, month)
                if (response.isSuccessful()) {
                    response.body()?.apply {
                        if(!monthlyList.contains(MonthlyModel(month, data))) {
                            monthlyList.add(MonthlyModel(month, data))
                        }
                        monthlyList.forEach {
                            if(month == it.id) {
                                monthlyAdapter.differ.submitList(it.data.jadwal)
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }catch (Ex:Exception){
                Log.e("Error",Ex.localizedMessage)
            }
        }
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

}