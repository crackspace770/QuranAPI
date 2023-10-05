package com.fajar.quranapi.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListSurahsResponse(

	@field:SerializedName("SurahsResponse")
	val surahsResponse: List<SurahsResponse>
)

data class SurahsResponse(

	@field:SerializedName("number")
	val number: Int,

	@field:SerializedName("numberOfAyahs")
	val numberOfAyahs: Int,

	@field:SerializedName("revelation")
	val revelation: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("translation")
	val translation: String,


)
