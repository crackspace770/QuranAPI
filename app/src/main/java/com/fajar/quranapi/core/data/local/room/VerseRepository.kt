package com.fajar.quranapi.core.data.local.room

import android.app.Application
import androidx.lifecycle.LiveData
import com.fajar.quranapi.core.data.local.entity.VerseEntity

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

    fun insert(favorite: VerseEntity) = quranDao.insert(favorite)

    fun isVerseBookmarked(number: String): LiveData<Boolean> = quranDao.isVerseBookmarked(number)

    fun check(number: String) = quranDao.check(number)

    fun delete(number: String) = quranDao.delete(number)

}