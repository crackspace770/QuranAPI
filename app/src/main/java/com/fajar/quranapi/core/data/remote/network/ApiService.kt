package com.fajar.quranapi.core.data.remote.network

import com.fajar.quranapi.core.data.remote.response.AyahResponse
import com.fajar.quranapi.core.data.remote.response.AyahsResponse
import com.fajar.quranapi.core.data.remote.response.DoaResponse
import com.fajar.quranapi.core.data.remote.response.JuzResponse
import com.fajar.quranapi.core.data.remote.response.ListDoaResponse
import com.fajar.quranapi.core.data.remote.response.ListSurahResponse
import com.fajar.quranapi.core.data.remote.response.SurahsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("surahs")
    fun getSurah(): Call<List<SurahsResponse>>

    @GET("surahs")
    fun getSurahs(): ListSurahResponse

    @GET("juz/{juzNum}/en.assad")
    fun getJuz(
        @Path("juzNum") juzNum:Int,
    ):Call<JuzResponse>

    @GET("surahs/{surahNum}")
    fun getSurahDetail(
        @Path("surahNum") surahNum: Int,
    ): Call<AyahsResponse>

    @GET("surahs/{surahNum}")
    fun getSurahsDetail(
        @Path("surahNum") surahNum: Int,
    ): AyahsResponse

    @GET("api")
    fun getDoa(): Call<List<DoaResponse>>


}