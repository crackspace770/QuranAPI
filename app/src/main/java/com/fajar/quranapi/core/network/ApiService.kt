package com.fajar.quranapi.core.network

import com.fajar.quranapi.core.response.AyahResponse
import com.fajar.quranapi.core.response.AyahsResponse
import com.fajar.quranapi.core.response.DoaResponse
import com.fajar.quranapi.core.response.JuzResponse
import com.fajar.quranapi.core.response.ListDoaResponse
import com.fajar.quranapi.core.response.ListSurahResponse
import com.fajar.quranapi.core.response.SurahsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("surahs")
    fun getSurah(): Call<List<SurahsResponse>>

    @GET("juz/{juzNum}/en.assad")
    fun getJuz(
        @Path("juzNum") juzNum:Int,
    ):Call<JuzResponse>

    @GET("surahs/{surahNum}")
    fun getSurahDetail(
        @Path("surahNum") surahNum: Int,
    ): Call<AyahsResponse>

    @GET("api")
    fun getDoa(): Call<List<DoaResponse>>


}