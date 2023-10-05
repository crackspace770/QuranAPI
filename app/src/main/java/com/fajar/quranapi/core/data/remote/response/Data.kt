package com.fajar.quranapi.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class Data(

    @field:SerializedName("number")
    val number: Int,

    @field:SerializedName("englishName")
    val englishName: String,

    @field:SerializedName("numberOfAyahs")
    val numberOfAyahs: Int,

    @field:SerializedName("revelationType")
    val revelationType: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("ayahs")
    val ayahs: List<AyahsItem>,

    @field:SerializedName("englishNameTranslation")
    val englishNameTranslation: String
)