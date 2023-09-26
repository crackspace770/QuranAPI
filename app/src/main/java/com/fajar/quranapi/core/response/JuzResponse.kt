package com.fajar.quranapi.core.response

import com.google.gson.annotations.SerializedName

data class JuzResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: JuzItem,

	@field:SerializedName("status")
	val status: String
)

data class JuzItem(

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

data class Surahs(

	@field:SerializedName("1")
	val jsonMember1: JsonMember1,

	@field:SerializedName("2")
	val jsonMember2: JsonMember2
)

data class Surah(

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

	@field:SerializedName("englishNameTranslation")
	val englishNameTranslation: String
)

data class JsonMember1(

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

	@field:SerializedName("englishNameTranslation")
	val englishNameTranslation: String
)

data class JsonMember2(

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

	@field:SerializedName("englishNameTranslation")
	val englishNameTranslation: String
)

data class Edition(

	@field:SerializedName("identifier")
	val identifier: String,

	@field:SerializedName("englishName")
	val englishName: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("format")
	val format: String,

	@field:SerializedName("language")
	val language: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("direction")
	val direction: String
)


