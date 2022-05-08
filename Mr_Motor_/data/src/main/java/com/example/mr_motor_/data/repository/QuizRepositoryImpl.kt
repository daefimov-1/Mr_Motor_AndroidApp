package com.example.mr_motor_.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.data.datasource.retrofit.ApiClient
import com.example.mr_motor_.data.datasource.storage.TokenStorage
import com.example.mr_motor_.data.models.QuizResultResponse
import com.example.mr_motor_.data.models.ShortQuizesResponse
import com.example.mr_motor_.domain.models.quiz.QuizResultVO
import com.example.mr_motor_.domain.models.quiz.QuizVO
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO
import com.example.mr_motor_.domain.repository.QuizRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizRepositoryImpl(private val tokenStorage: TokenStorage) : QuizRepository {
    override fun getQuizById(id_quiz: Long, resultLiveMutable: MutableLiveData<QuizVO>) {
        ApiClient.getApiService().getQuiz(id_quiz, tokenStorage.fetchAuthToken())
            .enqueue(object :
                Callback<QuizVO> {
                override fun onFailure(call: Call<QuizVO>, t: Throwable) {
                    t.printStackTrace()
                    resultLiveMutable.value = null
                }

                override fun onResponse(call: Call<QuizVO>, response: Response<QuizVO>) {
                    resultLiveMutable.value = response.body()
                }
            })
    }

    override fun getQuizzesResults(resultLiveMutable: MutableLiveData<List<QuizResultVO>>) {
        ApiClient.getApiService().getQuizesResults(tokenStorage.fetchAuthToken()).enqueue(object :
            Callback<QuizResultResponse> {
            override fun onFailure(call: Call<QuizResultResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<QuizResultResponse>,
                response: Response<QuizResultResponse>
            ) {
                resultLiveMutable.value = response.body()?.quizResults
            }
        })
    }

    override fun getUserQuizzes(resultLiveMutable: MutableLiveData<List<ShortQuizVO>>) {
        ApiClient.getApiService().getMyQuizzes(tokenStorage.fetchAuthToken()).enqueue(object :
            Callback<ShortQuizesResponse> {
            override fun onFailure(call: Call<ShortQuizesResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(
                call: Call<ShortQuizesResponse>,
                response: Response<ShortQuizesResponse>
            ) {
                resultLiveMutable.value = response.body()?.quizzes
            }
        })
    }

    override fun getAllShortQuizzes(resultLiveMutable: MutableLiveData<List<ShortQuizVO>>) {
        ApiClient.getApiService().getShortQuizzes().enqueue(object : Callback<ShortQuizesResponse> {
            override fun onFailure(call: Call<ShortQuizesResponse>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ShortQuizesResponse>, response: Response<ShortQuizesResponse>) {
                resultLiveMutable.value = response.body()?.quizzes
            }
        })
    }

    override fun updateResultOfQuiz(right_answers: Int, quiz_id: Long) {
        ApiClient.getApiService()
            .postResultOfQuiz(right_answers, quiz_id, tokenStorage.fetchAuthToken())
            .enqueue(object :
                Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {

                }
            })
    }
}