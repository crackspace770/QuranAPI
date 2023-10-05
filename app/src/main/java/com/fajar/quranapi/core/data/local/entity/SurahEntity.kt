package com.fajar.quranapi.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "surah")
data class SurahEntity(

    @PrimaryKey
    @ColumnInfo(name = "number")
    val number: Int,

    @ColumnInfo(name ="englishName")
    val englishName: String? = null,

    @ColumnInfo(name = "numberOfAyahs")
    val numberOfAyahs: Int? = null,

    @ColumnInfo(name = "revelationType")
    val revelationType: String? = null,

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "englishNameTranslation")
    val englishNameTranslation: String? = null


)