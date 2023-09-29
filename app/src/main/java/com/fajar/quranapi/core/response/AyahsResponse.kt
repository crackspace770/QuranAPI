package com.fajar.quranapi.core.response

import com.google.gson.annotations.SerializedName

data class AyahsResponse(

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

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("audio")
	val audio: String,

	@field:SerializedName("ayahs")
	val ayahs: List<AyahsItem>
)


data class Number(

	@field:SerializedName("inQuran")
	val inQuran: Int,

	@field:SerializedName("inSurah")
	val inSurah: Int
)

data class AyahsItem(

	@field:SerializedName("number")
	val number: Number,

	@field:SerializedName("translation")
	val translation: String,

	@field:SerializedName("audio")
	val audio: Audio,

	@field:SerializedName("arab")
	val arab: String
)



data class Audio(

	@field:SerializedName("muhammadjibreel")
	val muhammadjibreel: String,

	@field:SerializedName("husarymujawwad")
	val husarymujawwad: String,

	@field:SerializedName("minshawi")
	val minshawi: String,

	@field:SerializedName("alafasy")
	val alafasy: String,

	@field:SerializedName("muhammadayyoub")
	val muhammadayyoub: String,

	@field:SerializedName("ahmedajamy")
	val ahmedajamy: String
)



