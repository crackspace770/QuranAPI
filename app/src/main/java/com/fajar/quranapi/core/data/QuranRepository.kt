package com.fajar.quranapi.core.data

import com.fajar.quranapi.core.data.local.LocalDataSource
import com.fajar.quranapi.core.data.remote.RemoteDataSource
import com.fajar.quranapi.core.data.remote.network.ApiResponse
import com.fajar.quranapi.core.data.remote.response.SurahResponse
import com.fajar.quranapi.core.domain.model.Ayahs
import com.fajar.quranapi.core.domain.model.Surah
import com.fajar.quranapi.core.domain.repository.IQuranRepository
import com.fajar.quranapi.core.utils.AppExecutors
import com.fajar.quranapi.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class QuranRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :IQuranRepository {

    companion object {
        @Volatile
        private var instance: QuranRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): QuranRepository =
            instance ?: synchronized(this) {
                instance ?: QuranRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllSurah(): Flow<Resource<List<Surah>>> =
        object : NetworkBoundResource<List<Surah>, List<SurahResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Surah>> {
                return localDataSource.getAllSurah().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Surah>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<SurahResponse>>> =
                remoteDataSource.getAllSurah()

            override suspend fun saveCallResult(data: List<SurahResponse>) {
                val surahList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertSurah(surahList)
            }
        }.asFlow()

}