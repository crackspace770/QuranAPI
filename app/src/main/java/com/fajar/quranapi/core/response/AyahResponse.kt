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



data class AyahsItem(

	@SerializedName("audio")
	val audio: String?,

	@SerializedName("text")
	val text: String?,

	@SerializedName("numberInSurah")
	var verseNumber: Int?,

	@field:SerializedName("juz")
	val juz: Int
)

