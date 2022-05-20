package com.example.mr_motor_.data.datasource.retrofit

import com.example.mr_motor_.data.models.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit instance class
 */
class ApiClient {

    companion object {

        private lateinit var apiService: ApiService
        fun getApiService(): ApiService {

            // Initialize ApiService if not initialized yet
            if (!::apiService.isInitialized) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create()) //For Strings
                    .addConverterFactory(GsonConverterFactory.create()) //For Json
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //For RXJava
                    .build()

                apiService = retrofit.create(ApiService::class.java)
            }

            return apiService
        }

        fun getLongerConnectionApiService(): ApiService {
            val client: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build()
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create()) //For Strings
                .addConverterFactory(GsonConverterFactory.create()) //For Json
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //For RXJava
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}