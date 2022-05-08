package com.example.mr_motor_.data.datasource.retrofit

import com.example.mr_motor_.data.models.*
import com.example.mr_motor_.domain.models.quiz.QuizVO
import com.example.mr_motor_.data.models.Constants
import com.example.mr_motor_.domain.models.UserResponse
import retrofit2.Call
import retrofit2.http.*

/**
 * Interface for defining REST request functions
 */
interface ApiService {

    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST(Constants.FORGOTPASSWORD_URL)
    fun forgotPassword(@Body request: ForgotPasswordRequest): Call<String>

    @POST(Constants.SIGNUP_URL)
    fun signUp(@Body request: SignUpRequest): Call<UserResponse>

    @PUT(Constants.UPDATE_URL)
    fun update(
        @Body request: SignUpRequest,
        @Header("Authorization") authHeader: String?
    ): Call<UserResponse>

    @GET(Constants.DETAILS_URL)
    fun get_details(
        @Header("Authorization") authHeader: String?
    ): Call<UserResponse>

    @GET(Constants.NEWS_URL)
    fun getNews(): Call<PostResponse>

    @GET(Constants.NEWS_URL)
    fun getNewsWithToken(
        @Header("Authorization") authHeader: String?
    ): Call<PostResponse>

    @GET(Constants.COMPETITIONS_URL)
    fun getCompetitions(): Call<PostResponse>

    @GET(Constants.COMPETITIONS_URL)
    fun getCompetitionsWithToken(
        @Header("Authorization") authHeader: String?
    ): Call<PostResponse>

    @GET(Constants.RACERS_URL)
    fun getRacers(): Call<PostResponse>

    @GET(Constants.RACERS_URL)
    fun getRacersWithToken(
        @Header("Authorization") authHeader: String?
    ): Call<PostResponse>

    @GET(Constants.CARS_URL)
    fun getCars(): Call<PostResponse>

    @GET(Constants.CARS_URL)
    fun getCarsWithToken(
        @Header("Authorization") authHeader: String?
    ): Call<PostResponse>

    @GET(Constants.QUIZ_URL)
    fun getShortQuizzes(): Call<ShortQuizesResponse>

    @GET("quiz/{quiz_id}")
    fun getQuiz(
        @Path("quiz_id") quiz_id: Long,
        @Header("Authorization") authHeader: String?
    ): Call<QuizVO>

    @POST("posts/like")
    fun like(
        @Query("id") id: Long,
        @Header("Authorization") authHeader: String?
    ): Call<String>

    @GET("posts/liked")
    fun getLikedPosts(
        @Header("Authorization") authHeader: String?
    ): Call<PostResponse>

    @GET("quiz/results")
    fun getQuizesResults(
        @Header("Authorization") authHeader: String?
    ): Call<QuizResultResponse>

    @GET("quiz/my")
    fun getMyQuizzes(
        @Header("Authorization") authHeader: String?
    ): Call<ShortQuizesResponse>

    @POST("quiz/result")
    fun postResultOfQuiz(
        @Query("achieved") achieved: Int,
        @Query("id") id: Long,
        @Header("Authorization") authHeader: String?
    ): Call<String>

}