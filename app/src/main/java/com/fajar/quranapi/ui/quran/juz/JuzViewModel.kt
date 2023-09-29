package com.fajar.quranapi.ui.quran.juz


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajar.quranapi.core.network.ApiConfig
import com.fajar.quranapi.core.response.JuzItem
import com.fajar.quranapi.core.response.JuzResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JuzViewModel: ViewModel() {

    private val _juzList = MutableLiveData<List<JuzItem>>()
    val juzList: LiveData<List<JuzItem>> = _juzList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchAllJuzItems()
    }

    private fun fetchAllJuzItems() {
        _isLoading.value = true
        val apiService = ApiConfig.provideJuzApiService()
        val allJuzItems = mutableListOf<JuzItem>()
        // Fetch each Juz item one by one and add it to the list
        for (juzNumber in 1..30) { // Assuming you want to fetch all 30 Juz
            apiService.getJuz(juzNumber)
                .enqueue(object : Callback<JuzResponse> {
                    override fun onResponse(call: Call<JuzResponse>, response: Response<JuzResponse>) {
                        if (response.isSuccessful) {
                            val juzResponse = response.body()
                            juzResponse?.let {
                                allJuzItems.add(it.data)
                            }
                        }

                        // Check if we have fetched all 30 Juz items
                        if (allJuzItems.size == 30) {
                            // Once all items are fetched, update the LiveData
                            _juzList.value = allJuzItems.toList()
                            _isLoading.value = false
                        }
                    }

                    override fun onFailure(call: Call<JuzResponse>, t: Throwable) {
                        // Handle the network request failure here
                        _isLoading.value = false
                    }
                })
        }
    }
}