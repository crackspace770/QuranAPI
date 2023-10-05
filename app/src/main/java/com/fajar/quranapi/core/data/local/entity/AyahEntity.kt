package com.fajar.quranapi.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fajar.quranapi.core.data.remote.response.AyahsItem
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ayahs")
data class AyahEntity (

    @PrimaryKey
    @ColumnInfo(name = "number")
    var number: Int,

    @ColumnInfo(name = "numberOfAyahs")
    var numberOfAyahs: Int,

    @ColumnInfo(name = "revelation")
    var revelation: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "translation")
    var translation: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "audio")
    var audio: String,

    @ColumnInfo(name = "ayahs")
    var ayahs: List<AyahsItem>

)