package com.fajar.quranapi.core.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.fajar.quranapi.core.data.remote.network.ApiResponse
import com.fajar.quranapi.core.data.remote.network.ApiService
import com.fajar.quranapi.core.data.remote.response.AyahsResponse
import com.fajar.quranapi.core.data.remote.response.SurahResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext


class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }



    suspend fun getAllSurah(): Flow<ApiResponse<List<SurahResponse>>> {
        // val resultData = MutableLiveData<ApiResponse<List<SurahResponse>>>()
        return flow {
            try {
                val response = apiService.getSurahs()
                val dataArray = response.list
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.list))
                } else {
                    emit(ApiResponse.Empty(response.list))
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }



    suspend fun getSurahDetail(surahNum: Int): Flow<ApiResponse<AyahsResponse>> {
        return flow {
            try {
                val response = apiService.getSurahsDetail(surahNum)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "Failed to Get Movie Detail")
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }




}
