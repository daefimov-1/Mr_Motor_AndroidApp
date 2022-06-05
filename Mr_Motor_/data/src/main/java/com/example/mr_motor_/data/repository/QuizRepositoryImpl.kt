package com.example.mr_motor_.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.data.datasource.retrofit.ApiClient
import com.example.mr_motor_.data.datasource.storage.TokenStorage
import com.example.mr_motor_.data.models.QuizResultResponse
import com.example.mr_motor_.data.models.ShortQuizesResponse
import com.example.mr_motor_.domain.models.quiz.QuizResultVO
import com.example.mr_motor_.domain.models.quiz.QuizVO
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO
import com.example.mr_motor_.domain.repository.QuizRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class QuizRepositoryImpl(private val tokenStorage: TokenStorage) : QuizRepository {
    override fun getQuizById(id_quiz: Long, resultLiveMutable: MutableLiveData<QuizVO>) {
        ApiClient.getApiService().getQuiz(id_quiz, tokenStorage.fetchAuthToken())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<QuizVO> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(t: QuizVO?) {
                    resultLiveMutable.value = t
                }

                override fun onError(e: Throwable?) {
                    Log.e("QUIZ_REPO", e.toString())
                    resultLiveMutable.value = null
                }

                override fun onComplete() {
                }

            })
    }

    override fun getQuizzesResults(resultLiveMutable: MutableLiveData<List<QuizResultVO>>) {
        ApiClient.getApiService().getQuizesResults(tokenStorage.fetchAuthToken())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<QuizResultResponse> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(t: QuizResultResponse?) {
                    resultLiveMutable.value = t?.quizResults
                }

                override fun onError(e: Throwable?) {
                    Log.e("QUIZ_REPO", e.toString())
                    resultLiveMutable.value = null
                }

                override fun onComplete() {
                }

            })
    }

    override fun getUserQuizzes(resultLiveMutable: MutableLiveData<List<ShortQuizVO>>) {
        ApiClient.getApiService().getMyQuizzes(tokenStorage.fetchAuthToken())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ShortQuizesResponse> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(t: ShortQuizesResponse?) {
                    resultLiveMutable.value = t?.quizzes
                }

                override fun onError(e: Throwable?) {
                    Log.e("QUIZ_REPO", e.toString())
                    resultLiveMutable.value = null
                }

                override fun onComplete() {
                }

            })
    }

    override fun getAllShortQuizzes(resultLiveMutable: MutableLiveData<List<ShortQuizVO>>) {
        ApiClient.getApiService().getShortQuizzes()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ShortQuizesResponse> {
                override fun onSubscribe(d: Disposable?) {
                }

                override fun onNext(t: ShortQuizesResponse?) {
                    resultLiveMutable.value = t?.quizzes
                }

                override fun onError(e: Throwable?) {
                    Log.e("QUIZ_REPO", e.toString())
                    resultLiveMutable.value = null
                }

                override fun onComplete() {
                }

            })
    }

    override fun updateResultOfQuiz(right_answers: Int, quiz_id: Long) {
        ApiClient.getApiService().postResultOfQuiz(right_answers, quiz_id, tokenStorage.fetchAuthToken())
    }
}