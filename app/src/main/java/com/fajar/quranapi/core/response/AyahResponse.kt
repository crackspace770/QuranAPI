package com.fajar.quranapi.core.response

import com.google.gson.annotations.SerializedName

data class AyahResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("status")
	val status: String
)

data class Data(

	@field:SerializedName("number")
	val number: Int,

	@field:SerializedName("englishName")
	val englishName: String,

	@field:SerializedName("numberOfAyahs")
	val numberOfAyahs: Int,

	@field:SerializedName("revelationType")
	val revelationType: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("ayahs")
	val ayahs: List<AyahsItem>,

	@field:SerializedName("englishNameTranslation")
	val englishNameTranslation: String
)

data class AyahsItem(

	@SerializedName("audio")
	val audio: String?,

	@SerializedName("text")
	val text: String?,

	@SerializedName("numberInSurah")
	var verseNumber: Int?
)

