package com.fajar.quranapi.core.data.remote.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class ApiConfig {
        companion object{
            private fun provideOkHttpClient(): OkHttpClient {
                return OkHttpClient.Builder()
                    .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()
            }

            fun provideApiService(): ApiService {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://quran-api-id.vercel.app/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(provideOkHttpClient())
                    .build()
                return retrofit.create(ApiService::class.java)
            }

            fun provideJuzApiService(): ApiService {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.alquran.cloud/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(provideOkHttpClient())
                    .build()
                return retrofit.create(ApiService::class.java)
            }

            fun provideDoaApiService(): ApiService {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://doa-doa-api-ahmadramadhan.fly.dev/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(provideOkHttpClient())
                    .build()
                return retrofit.create(ApiService::class.java)
            }

            fun provideSholatService():ApiService{
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.banghasan.com/sholat/format/json/jadwal/kota/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(provideOkHttpClient())
                    .build()
                return retrofit.create(ApiService::class.java)
            }

            fun provideShalatService():ApiService{
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.myquran.com/v1/sholat/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(provideOkHttpClient())
                    .build()
                return retrofit.create(ApiService::class.java)
            }

        }
    }

