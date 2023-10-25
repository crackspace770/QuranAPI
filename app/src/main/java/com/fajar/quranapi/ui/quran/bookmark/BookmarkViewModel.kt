package com.fajar.quranapi.ui.quran.bookmark

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajar.quranapi.core.data.local.entity.SurahEntity
import com.fajar.quranapi.core.data.local.entity.VerseEntity
import com.fajar.quranapi.core.data.local.room.VerseRepository
import com.fajar.quranapi.core.data.remote.response.AyahsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarkViewModel(application: Application) : ViewModel() {

    private val verseRepository: VerseRepository = VerseRepository(application)

    // Function to insert a bookmarked verse
    fun insertBookmark(verse: VerseEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            verseRepository.insert(verse)
        }
    }

    fun getBookmarkedVerses() = verseRepository.getBookmarkedVerses()



    // Function to check if a verse is bookmarked
    fun isVerseBookmarked(number: String): LiveData<Boolean> {
        return verseRepository.isVerseBookmarked(number)
    }

    // Function to delete a bookmarked verse
    fun deleteBookmark(number: String) {
        viewModelScope.launch(Dispatchers.IO) {
            verseRepository.delete(number)
        }
    }

    // Function to get the last read verses
    fun getLastReadVerses(surahNum: Int): LiveData<List<VerseEntity>> =
        verseRepository.getLastReadVerses(surahNum)




}