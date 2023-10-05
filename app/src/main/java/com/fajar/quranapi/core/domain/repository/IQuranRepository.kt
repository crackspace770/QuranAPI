package com.fajar.quranapi.core.domain.repository

import com.fajar.quranapi.core.data.Resource
import com.fajar.quranapi.core.domain.model.Ayahs
import com.fajar.quranapi.core.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface IQuranRepository {
    fun getAllSurah(): Flow<Resource<List<Surah>>>

  //  fun getSurahDetail(surah: Ayahs): Flow<Resource<Ayahs>>

  //  fun getFavoriteSurah(): Flow<List<Surah>>

  //  fun setFavoriteSurah(surah: Surah, state: Boolean)
}