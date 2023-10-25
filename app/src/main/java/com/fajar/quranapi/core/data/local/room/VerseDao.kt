package com.fajar.quranapi.core.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fajar.quranapi.core.data.local.entity.VerseEntity

@Dao
interface VerseDao {

    @Query("SELECT count(*) FROM verse WHERE verse.number = :number")
    fun check(number: String): Int

    @Insert
    fun insert(verse: VerseEntity)

    @Query("DELETE FROM verse WHERE verse.number = :number")
    fun delete(number: String): Int

    @Query("SELECT * FROM verse")
    fun getAllBookmark(): LiveData<List<VerseEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM verse WHERE number = :number LIMIT 1)")
    fun isVerseBookmarked(number: String): LiveData<Boolean>

    @Query("SELECT * FROM verse WHERE number = :surahNum AND isRead = 1")
    fun getLastReadVerses(surahNum: Int): LiveData<List<VerseEntity>>
}