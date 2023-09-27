package com.fajar.quranapi.ui.quran.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajar.quranapi.core.network.ApiConfig
import com.fajar.quranapi.core.response.AyahResponse
import com.fajar.quranapi.core.response.AyahsItem
import com.fajar.quranapi.core.response.Data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    private val _ayahDetail = MutableLiveData<Data?>() // LiveData for the detailed information
    val ayahDetail: MutableLiveData<Data?> = _ayahDetail

    private val _ayahsList = MutableLiveData<List<AyahsItem>>() // LiveData for the list of ayahs
    val ayahsList: LiveData<List<AyahsItem>> = _ayahsList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String> = _snackbarText

    // Function to fetch the detail of a specific item (ayah)
    fun fetchAyahDetail(surahNum: Int) {
        _isLoading.value = true

        val client = ApiConfig.provideApiService().getSurahDetail(surahNum)
        client.enqueue(object : Callback<AyahResponse> {
            override fun onResponse(call: Call<AyahResponse>, response: Response<AyahResponse>) {
                if (response.isSuccessful) {
                    val ayahData = response.body()?.data
                    if (ayahData != null) {
                        _ayahDetail.value = ayahData
                        _ayahsList.value = ayahData.ayahs
                    } else {
                        _snackbarText.value = "Ayah data is null" // Handle null data case
                    }
                } else {
                    _snackbarText.value = "Failed to fetch Ayah detail" // Handle API request failure
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<AyahResponse>, t: Throwable) {
                _snackbarText.value = "Failed to fetch Ayah detail: ${t.message}" // Handle network failure
                _isLoading.value = false
            }
        })
    }

}