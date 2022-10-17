package com.example.githubsearch.data.api

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class RetrofitClient {
    private val baseUrl = "https://api.github.com/"

//    private val retrofit = Retrofit.Builder()
//        .baseUrl(baseUrl)
//        .build()
//
//    val apiService: ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideRetrofit() :ApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}