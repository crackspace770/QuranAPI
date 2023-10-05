package com.fajar.quranapi.ui.doa

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fajar.quranapi.core.data.remote.network.ApiConfig
import com.fajar.quranapi.core.data.remote.response.DoaResponse

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoaViewModel : ViewModel() {

    private val _listDoa = MutableLiveData<List<DoaResponse>>()
    val listDoa: LiveData<List<DoaResponse>> = _listDoa

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String> = _snackbarText

    init {
        findDoa()
    }

    private fun findDoa() {
        _isLoading.value = true
        val client = ApiConfig.provideDoaApiService().getDoa()
        client.enqueue(object : Callback<List<DoaResponse>> { // Change the response type
            override fun onResponse(call: Call<List<DoaResponse>>, response: Response<List<DoaResponse>>) {
                if (response.isSuccessful) {
                    _listDoa.value = response.body() ?: emptyList() // Update this line
                    Log.d(TAG, "onResponse: ${response.body()}")
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<List<DoaResponse>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                _isLoading.value = false
            }
        })
    }

    companion object {
        private const val TAG = "DoaViewModel"
    }
}