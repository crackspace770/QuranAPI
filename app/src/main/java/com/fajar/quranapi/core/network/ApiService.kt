package com.fajar.quranapi.core.network

import com.fajar.quranapi.core.response.AyahResponse
import com.fajar.quranapi.core.response.ListSurahResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("surah")
    fun getSurah(): Call<ListSurahResponse>

    @GET("surah/{surahNum}/ar.alafasy")
    fun getSurahDetail(
        @Path("surahNum") surahNum: Int,
    ): Call<AyahResponse>


}