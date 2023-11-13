package com.fajar.quranapi.core.data.local.room

import android.app.Application
import androidx.lifecycle.LiveData
import com.fajar.quranapi.core.data.local.entity.VerseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VerseRepository(application: Application) {

    private val quranDao: VerseDao

    init {
        val db = VerseDatabase.getDatabase(application)
        quranDao = db.verseDao()
    }

    fun getLastReadVerses(surahNum: Int): LiveData<List<VerseEntity>> =
        quranDao.getLastReadVerses(surahNum)

    fun getAllBookmark(): LiveData<List<VerseEntity>> = quranDao.getAllBookmark()

    fun getBookmarkedVerses() = quranDao.getAllBookmark()

    suspend fun insert(verse: VerseEntity) {
        withContext(Dispatchers.IO) {
            quranDao.insert(verse)
        }
    }

    fun isVerseBookmarked(number: String): LiveData<Boolean> = quranDao.isVerseBookmarked(number)

    fun check(number: String) = quranDao.check(number)

    suspend fun delete(number: String) {
        withContext(Dispatchers.IO) {
            quranDao.delete(number)
        }
    }

}