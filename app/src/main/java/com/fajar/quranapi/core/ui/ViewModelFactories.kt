package com.fajar.quranapi.core.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fajar.quranapi.ui.quran.bookmark.BookmarkViewModel
import com.fajar.quranapi.ui.quran.detail.DetailViewModel

class ViewModelFactories constructor(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                BookmarkViewModel(application) as T
            }
            else -> throw  IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactories? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactories {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactories(application)
                }
            }
            return INSTANCE as ViewModelFactories
        }
    }
}