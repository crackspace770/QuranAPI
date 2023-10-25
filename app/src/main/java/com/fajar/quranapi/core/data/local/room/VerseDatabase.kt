package com.fajar.quranapi.core.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fajar.quranapi.core.data.local.entity.VerseEntity

@Database(entities = [VerseEntity::class], version = 1)
abstract class VerseDatabase: RoomDatabase() {

    abstract fun verseDao(): VerseDao

    companion object {
        @Volatile
        private var INSTANCE: VerseDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): VerseDatabase {
            if (INSTANCE == null) {
                synchronized(VerseDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        VerseDatabase::class.java,
                        "verse_db"
                    ).build()
                }
            }
            return INSTANCE as VerseDatabase
        }
    }
}