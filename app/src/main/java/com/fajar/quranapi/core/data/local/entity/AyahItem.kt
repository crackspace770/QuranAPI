package com.fajar.quranapi.core.data.local.entity

import com.fajar.quranapi.core.data.remote.response.Audio
import com.fajar.quranapi.core.data.remote.response.Number
import com.google.gson.annotations.SerializedName

data class AyahItem (
    @field:SerializedName("number")
    val number: String,

    @field:SerializedName("translation")
    val translation: String,

    @field:SerializedName("audio")
    val audio: String,

    @field:SerializedName("arab")
    val arab: String
)