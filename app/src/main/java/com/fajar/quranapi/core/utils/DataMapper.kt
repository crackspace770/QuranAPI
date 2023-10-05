package com.fajar.quranapi.core.utils


import com.fajar.quranapi.core.data.local.entity.SurahEntity
import com.fajar.quranapi.core.data.remote.response.SurahResponse
import com.fajar.quranapi.core.domain.model.Surah

object DataMapper {

    fun mapResponsesToEntities(input: List<SurahResponse>): List<SurahEntity> {
        val surahList = ArrayList<SurahEntity>()

        input.map {
            val surah = SurahEntity(
                it.number,
                it.englishName,
                it.numberOfAyahs,
                it.revelationType,
                it.name,
                it.englishNameTranslation,

            )
            surahList.add(surah)
        }
        return surahList
    }

    fun mapEntitiesToDomain(input: List<SurahEntity>): List<Surah> =
        input.map {
            Surah(
                it.number,
                it.englishName,
                it.numberOfAyahs,
                it.revelationType,
                it.name,
                it.englishNameTranslation,

            )
        }
}