package com.fajar.quranapi.ui.quran.detail

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.quranapi.core.data.local.entity.VerseEntity
import com.fajar.quranapi.core.data.local.room.VerseRepository
import com.fajar.quranapi.core.data.remote.network.ApiConfig

import com.fajar.quranapi.core.data.remote.response.AyahsItem
import com.fajar.quranapi.core.data.remote.response.AyahsResponse
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : ViewModel() {

    private val verseRepository: VerseRepository = VerseRepository(application)

    private val _ayahDetail = MutableLiveData<AyahsResponse?>()
    val ayahDetail: LiveData<AyahsResponse?> = _ayahDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String> = _snackbarText


    // Function to fetch the detail of a specific surah
    fun fetchSurahDetail(surahNum: Int) {
        _isLoading.value = true

        val client = ApiConfig.provideApiService().getSurahDetail(surahNum)
        client.enqueue(object : Callback<AyahsResponse> {
            override fun onResponse(call: Call<AyahsResponse>, response: Response<AyahsResponse>) {
                if (response.isSuccessful) {
                    val ayahsResponse = response.body()
                    if (ayahsResponse != null) {
                        _ayahDetail.value = ayahsResponse
                    } else {
                        _snackbarText.value = "Ayahs response is null"
                    }
                } else {
                    _snackbarText.value = "Failed to fetch Surah detail"
                }
                _isLoading.value = false
            }

            override fun onFailure(call: Call<AyahsResponse>, t: Throwable) {
                _snackbarText.value = "Failed to fetch Surah detail: ${t.message}"
                _isLoading.value = false
            }
        })
    }

    fun bookmarkVerse(verseItem: AyahsItem) {
        viewModelScope.launch {
            val verseEntity = VerseEntity(
                number = verseItem.number.inSurah.toString(),
                translation = verseItem.translation,
                audio = verseItem.audio.alafasy,
                arab = verseItem.arab
            )
            verseRepository.insert(verseEntity)
        }
    }


}