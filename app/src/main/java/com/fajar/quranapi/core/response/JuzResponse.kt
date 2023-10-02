package com.fajar.quranapi.core.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class JuzResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: JuzItem,

	@field:SerializedName("status")
	val status: String
)

@Parcelize
data class JuzItem(

	@field:SerializedName("number")
	val number: Int,

	@field:SerializedName("englishName")
	val englishName: String?,

	@field:SerializedName("numberOfAyahs")
	val numberOfAyahs: Int?,

	@field:SerializedName("revelationType")
	val revelationType: String?,

	@field:SerializedName("name")
	val name: String?,

	@field:SerializedName("ayahs")
	val ayahs: List<AyahItem>,

	@field:SerializedName("englishNameTranslation")
	val englishNameTranslation: String?
):Parcelable

@Parcelize
data class AyahItem(

	@field:SerializedName("number")
	val number: Int,

	@field:SerializedName("text")
	val text: String,

	@field:SerializedName("numberInSurah")
	val numberInSurah: Int,

	@field:SerializedName("juz")
	val juz: Int
):Parcelable


