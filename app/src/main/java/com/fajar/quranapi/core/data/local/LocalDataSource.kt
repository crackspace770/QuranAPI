package com.fajar.quranapi.core.data.local

import com.fajar.quranapi.core.data.local.entity.SurahEntity
import com.fajar.quranapi.core.data.local.room.QuranDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val surahDao: QuranDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(surahDao: QuranDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(surahDao)
            }
    }

    fun getAllSurah(): Flow<List<SurahEntity>> = surahDao.getAllSurah()

    //fun getFavoriteSurah(): Flow<List<SurahEntity>> = surahDao.getFavoriteSurah()

    suspend fun insertSurah(tourismList: List<SurahEntity>) = surahDao.insertSurah(tourismList)

   // fun setFavoriteSurah(surah: SurahEntity, newState: Boolean) {
  //      surah.isBookmarked = newState
  //      surahDao.updateFavoriteSurah(surah)
  //  }
}