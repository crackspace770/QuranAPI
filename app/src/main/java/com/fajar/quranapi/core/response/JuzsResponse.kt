package com.fajar.quranapi.core.response

import com.google.gson.annotations.SerializedName

data class JuzsResponse(

	@field:SerializedName("code")
	val code: Int? = null,

	@field:SerializedName("data")
	val data: JuzsItem? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class JuzsItem(

	@field:SerializedName("number")
	val number: Int? = null
)
