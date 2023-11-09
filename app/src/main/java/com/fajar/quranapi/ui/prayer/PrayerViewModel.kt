package com.fajar.quranapi.ui.prayer

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.quranapi.core.data.remote.network.ApiConfig
import com.fajar.quranapi.core.data.remote.network.ApiService
import com.fajar.quranapi.core.data.remote.response.ScheduleResponse
import com.fajar.quranapi.core.data.remote.response.SholatResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale
import java.util.Timer
import java.util.TimerTask

class PrayerViewModel() : ViewModel() {

    private val apiService: ApiService = ApiConfig.provideSholatService()

    private val _dailySchedule = MutableLiveData<ScheduleResponse.Data.Jadwal?>()
    val dailySchedule: LiveData<ScheduleResponse.Data.Jadwal?> = _dailySchedule

    private val _tomorrowSchedule = MutableLiveData<ScheduleResponse.Data.Jadwal?>()
    val tomorrowSchedule: LiveData<ScheduleResponse.Data.Jadwal?> = _tomorrowSchedule

    fun fetchDailySchedule(cityId: String, year: String, month: String, day: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getScheduleByDay(cityId, year, month, day)
                if (response.isSuccessful) {
                    _dailySchedule.value = response.body()?.data?.jadwal
                } else {
                    // Handle error
                }
            } catch (ex: Exception) {
                // Handle exception
            }
        }
    }

    fun fetchTomorrowSchedule(cityId: String, year: String, month: String, day: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getScheduleByDay(cityId, year, month, day)
                if (response.isSuccessful) {
                    _tomorrowSchedule.value = response.body()?.data?.jadwal
                } else {
                    // Handle error
                }
            } catch (ex: Exception) {
                // Handle exception
            }
        }
    }
    companion object {
        private const val TAG = "PrayerViewModel"
    }

}