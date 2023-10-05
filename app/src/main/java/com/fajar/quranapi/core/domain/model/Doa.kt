package com.fajar.quranapi.core.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doa (
    val ayat: String? = null,
    val doa: String? = null,
    val artinya: String? = null,
    val id: String? = null,
    val latin: String? = null

):Parcelable