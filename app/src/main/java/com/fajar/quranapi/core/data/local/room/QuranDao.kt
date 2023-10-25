package com.fajar.quranapi.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fajar.quranapi.core.data.local.entity.SurahEntity
import com.fajar.quranapi.core.data.local.entity.VerseEntity
import com.fajar.quranapi.core.data.remote.response.Number
import kotlinx.coroutines.flow.Flow

@Dao
interface QuranDao {


    @Query("SELECT * FROM surah")
    fun getAllSurah(): Flow<List<SurahEntity>>

  //  @Query("SELECT * FROM surah where isBookmarked = 1")
 //   fun getFavoriteSurah(): Flow<List<SurahEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSurah(quran: List<SurahEntity>)

  //  @Update
  //  fun updateFavoriteSurah(quran: SurahEntity)



}