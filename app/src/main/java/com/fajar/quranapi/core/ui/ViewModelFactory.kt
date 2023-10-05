package com.fajar.quranapi.core.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fajar.quranapi.core.di.Injection
import com.fajar.quranapi.core.domain.usecase.QuranUseCase
import com.fajar.quranapi.ui.quran.juz.JuzViewModel
import com.fajar.quranapi.ui.quran.surah.SurahViewModel
import com.fajar.quranapi.ui.quran.surah.SurahsViewModel

class ViewModelFactory private constructor(private val surahUseCase: QuranUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideSurahUseCase(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(SurahsViewModel::class.java) -> {
                SurahsViewModel(surahUseCase) as T
            }
           // modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
          //      BookmarkViewModel(surahUseCase) as T
          //  }
         //   modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
        //        DetailViewModel(surahUseCase) as T
        //    }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}