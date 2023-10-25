package com.fajar.quranapi.ui.quran.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fajar.quranapi.core.ui.ViewModelFactory


class DetailViewModelFactory constructor(private val application: Application) :
    ViewModelProvider.NewInstanceFactory()  {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(application) as T
            }
            else -> throw  IllegalArgumentException("Unknown ViewModel Class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: DetailViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): DetailViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = DetailViewModelFactory(application)
                }
            }
            return INSTANCE as DetailViewModelFactory
        }
    }

}