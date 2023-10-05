package com.fajar.quranapi.core.domain.model

import com.fajar.quranapi.core.data.remote.response.AyahsItem

data class Ayahs(
    val number: Int,
    val englishName: String?,
    val numberOfAyahs: Int?,
    val revelationType: String?,
    val name: String?,
    val englishNameTranslation: String?,
    val verseItem: List<AyahsItem>,
)