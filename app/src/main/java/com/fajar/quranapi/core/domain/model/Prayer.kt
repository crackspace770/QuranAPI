package com.fajar.quranapi.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Prayer (
    val prayerName: String,
    val prayerTime: String,
):Parcelable