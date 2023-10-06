package com.fajar.quranapi.core.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ListDoaResponse(

	@field:SerializedName("DoaResponse")
	val doaResponse: List<DoaResponse>
)

@Parcelize
data class DoaResponse(

	@field:SerializedName("ayat")
	val ayat: String? = null,

	@field:SerializedName("doa")
	val doa: String,

	@field:SerializedName("artinya")
	val artinya: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("latin")
	val latin: String? = null
):Parcelable
