package com.fajar.quranapi.core.domain.usecase

import com.fajar.quranapi.core.domain.repository.IQuranRepository

class QuranInteractor(private val surahRepository: IQuranRepository): QuranUseCase  {


    override fun getAllSurah() = surahRepository.getAllSurah()

  //  override fun getSurahDetail(surah: Ayahs) = surahRepository.getSurahDetail(surah)

   // override fun getFavoriteSurah() = surahRepository.getFavoriteSurah()

   // override fun setFavoriteSurah(tourism: Surah, state: Boolean) = surahRepository.setFavoriteSurah(tourism, state)
}