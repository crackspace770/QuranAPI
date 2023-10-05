package com.fajar.quranapi.ui.quran.surah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fajar.quranapi.core.domain.usecase.QuranUseCase

class SurahsViewModel(quranUseCase: QuranUseCase) : ViewModel()  {

    val surahs = quranUseCase.getAllSurah().asLiveData()

}