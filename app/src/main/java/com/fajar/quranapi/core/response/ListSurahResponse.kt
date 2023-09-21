package com.fajar.quranapi.core.response

import com.google.gson.annotations.SerializedName


data class ListSurahResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val list: List<SurahResponse>
)

data class SurahResponse(

    @field:SerializedName("number")
    val number: Int? = null,

    @field:SerializedName("englishName")
    val englishName: String? = null,

    @field:SerializedName("numberOfAyahs")
    val numberOfAyahs: Int? = null,

    @field:SerializedName("revelationType")
    val revelationType: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("englishNameTranslation")
    val englishNameTranslation: String? = null


)

