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

class PrayerViewModel(private val context: Context) : ViewModel() {

    private val apiService: ApiService = ApiConfig.provideSholatService()
    private val _shalatData = MutableLiveData<SholatResponse?>()
    val shalatData: MutableLiveData<SholatResponse?> = _shalatData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _locationAddress = MutableLiveData<Address>()
    val locationAddress get() = _locationAddress



    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDate(): String {
        val currentDate = LocalDate.now()
        return currentDate.toString() // The default format is ISO-8601 (e.g., "yyyy-MM-dd")
    }

    private fun getLocationAddress(context: Context, location: Location){
        viewModelScope.launch {
            Geocoder(context, Locale.getDefault()).apply {
                getFromLocation(location.latitude, location.longitude, 1)?.first()
                    .let { address ->
                        _locationAddress.value = address
                    }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getShalatTimesForCurrentDate(context: Context, cityId: Int, location: Location) {
        val currentDate = getCurrentDate()

        // Get location address (if needed)
        getLocationAddress(context, location)

        val call = apiService.getSholatSchedule(cityId, currentDate)
        call.enqueue(object : Callback<SholatResponse> {
            override fun onResponse(call: Call<SholatResponse>, response: Response<SholatResponse>) {
                if (response.isSuccessful) {
                    val shalatResponse = response.body()
                    shalatData.value = shalatResponse
                    Log.d(TAG, "onResponse: ${response.body()}")
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<SholatResponse>, t: Throwable) {
                // Update isLoading to false when the operation is complete (even in case of failure)
                _isLoading.value = false
            }
        })
    }

    companion object {
        private const val TAG = "PrayerViewModel"
    }

}