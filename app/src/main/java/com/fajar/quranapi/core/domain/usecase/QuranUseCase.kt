package com.fajar.quranapi.core.domain.usecase

import com.fajar.quranapi.core.data.Resource
import com.fajar.quranapi.core.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface QuranUseCase {

    fun getAllSurah(): Flow<Resource<List<Surah>>>
}