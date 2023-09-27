package com.fajar.quranapi.ui.quran.surah

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajar.quranapi.core.network.ApiConfig
import com.fajar.quranapi.core.response.ListSurahResponse
import com.fajar.quranapi.core.response.SurahResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SurahViewModel: ViewModel() {

    private val _listSurah = MutableLiveData<ArrayList<SurahResponse>>()
    val listSurah: LiveData<ArrayList<SurahResponse>> = _listSurah

    private val _namaSurah = MutableLiveData<ListSurahResponse>()
    val namaSurah: LiveData<ListSurahResponse> = _namaSurah

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String> = _snackbarText

    init {
        findSurah()
    }

    //search
    private fun findSurah() {
        _isLoading.value = true
        val client = ApiConfig.provideApiService().getSurah()
        client.enqueue(object : Callback<ListSurahResponse> {
            override fun onResponse(
                call: Call<ListSurahResponse>,
                response: Response<ListSurahResponse>
            ) {
                if (response.isSuccessful) {
                    _listSurah.value = response.body()?.list as ArrayList<SurahResponse>?
                    _namaSurah.value = response.body()
                    Log.d(TAG, "onResponse: ${response.body()?.message}")
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
                // Update isLoading to false when the operation is complete
                _isLoading.value = false
            }

            override fun onFailure(call: Call<ListSurahResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                // Update isLoading to false when the operation is complete (even in case of failure)
                _isLoading.value = false
            }
        })
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}