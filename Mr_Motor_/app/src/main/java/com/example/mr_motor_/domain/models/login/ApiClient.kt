package com.example.mr_motor_.domain.models.login

import android.util.Log
import com.example.mr_motor_.domain.objects.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit instance class
 */
class ApiClient {

    companion object{
        private lateinit var apiService: ApiService
        fun getApiService(): ApiService {

            // Initialize ApiService if not initialized yet
            if (!::apiService.isInitialized) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                apiService = retrofit.create(ApiService::class.java)
            }

            return apiService
        }
    }
}