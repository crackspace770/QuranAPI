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




