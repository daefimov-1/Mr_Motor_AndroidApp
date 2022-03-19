package com.example.mr_motor_.login

import com.example.mr_motor_.models.LoginRequest
import com.example.mr_motor_.models.LoginResponse
import com.example.mr_motor_.models.UserResponse
import com.example.mr_motor_.objects.Constants
import retrofit2.Call
import retrofit2.http.*

/**
 * Interface for defining REST request functions
 */
interface ApiService {

    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse> //Вопрос точно ли Call из библиотеки retrofit2!!!!!!!!!!1

    @GET(Constants.DETAILS_URL)
    fun get_details(
        @Header("Authorization") authHeader: String?
    ): Call<UserResponse>


}