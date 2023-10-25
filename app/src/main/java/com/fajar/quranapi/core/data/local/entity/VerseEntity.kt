package com.fajar.quranapi.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fajar.quranapi.core.data.remote.response.Audio
import com.fajar.quranapi.core.data.remote.response.Number


@Entity(tableName = "verse")
data class VerseEntity(
    @PrimaryKey
    @ColumnInfo(name = "number")
    val number: String,

    @ColumnInfo(name = "translation")
    val translation: String,

    @ColumnInfo(name = "audio")
    val audio: String,

    @ColumnInfo(name = "arab")
    val arab: String,

    @ColumnInfo(name = "isRead")
    var isRead: Boolean = false // Indicates if the verse has been read
)




