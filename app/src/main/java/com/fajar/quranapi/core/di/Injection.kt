package com.fajar.quranapi.core.di

import android.content.Context
import com.fajar.quranapi.core.data.QuranRepository
import com.fajar.quranapi.core.data.local.LocalDataSource
import com.fajar.quranapi.core.data.local.room.SurahDatabase
import com.fajar.quranapi.core.data.remote.RemoteDataSource
import com.fajar.quranapi.core.data.remote.network.ApiConfig
import com.fajar.quranapi.core.domain.repository.IQuranRepository
import com.fajar.quranapi.core.domain.usecase.QuranInteractor
import com.fajar.quranapi.core.domain.usecase.QuranUseCase
import com.fajar.quranapi.core.utils.AppExecutors

object Injection{
    private fun provideRepository(context: Context): IQuranRepository {
        val database = SurahDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.surahDao())
        val appExecutors = AppExecutors()

        return QuranRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideSurahUseCase(context: Context): QuranUseCase {
        val repository = provideRepository(context)
        return QuranInteractor(repository)
    }
}

