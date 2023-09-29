package com.fajar.quranapi.core.network

import com.fajar.quranapi.core.response.AyahResponse
import com.fajar.quranapi.core.response.DoaResponse
import com.fajar.quranapi.core.response.JuzResponse
import com.fajar.quranapi.core.response.ListDoaResponse
import com.fajar.quranapi.core.response.ListSurahResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("surah")
    fun getSurah(): Call<ListSurahResponse>

    @GET("juz/{juzNum}/en.assad")
    fun getJuz(
        @Path("juzNum") juzNum:Int,
    ):Call<JuzResponse>

    @GET("surah/{surahNum}/ar.alafasy")
    fun getSurahDetail(
        @Path("surahNum") surahNum: Int,
    ): Call<AyahResponse>

    @GET("api")
    fun getDoa(): Call<List<DoaResponse>>


}