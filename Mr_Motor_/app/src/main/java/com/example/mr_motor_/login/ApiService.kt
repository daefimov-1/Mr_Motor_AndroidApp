package com.example.mr_motor_.login

import com.example.mr_motor_.models.*
import com.example.mr_motor_.objects.Constants
import retrofit2.Call
import retrofit2.http.*

/**
 * Interface for defining REST request functions
 */
interface ApiService {

    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(Constants.DETAILS_URL)
    fun get_details(
        @Header("Authorization") authHeader: String?
    ): Call<UserResponse>

    @POST(Constants.FORGOTPASSWORD_URL)
    fun forgotPassword(@Body request: ForgotPasswordRequest): Call<String>

    @POST(Constants.SIGNUP_URL)
    fun signUp(@Body request: SignUpRequest): Call<UserResponse>

    @PUT(Constants.UPDATE_URL)
    fun update(
        @Body request: SignUpRequest,
        @Header("Authorization") authHeader: String?
    ): Call<UserResponse>
}