package com.fajar.quranapi.core.model

import android.os.Parcelable


data class Surah(
    val number: Int,
    val englishName: String?,
    val numberOfAyahs: Int?,
    val revelationType: String?,
    val name: String?,
    val englishNameTranslation: String?,
)
