package com.fajar.quranapi.core.data.local.entity

import com.fajar.quranapi.core.data.remote.response.MonthlyScheduleResponse

data class MonthlyModel (
    val id: String,
    val data: MonthlyScheduleResponse.Data
)
