package com.fajar.quranapi.core.preference

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveLastReadSurahNumber(surahNum: Int) {
        sharedPreferences.edit().putInt("lastReadSurahNum", surahNum).apply()
    }

    fun saveLastReadVerseNumber(verseNum: Int) {
        sharedPreferences.edit().putInt("lastReadVerseNum", verseNum).apply()
    }

    fun getLastReadSurahNumber(): Int {
        return sharedPreferences.getInt("lastReadSurahNum", 1)
    }

    fun getLastReadVerseNumber(): Int {
        return sharedPreferences.getInt("lastReadVerseNum", 1)
    }
}
