package com.example.mr_motor_.presentation.tasks.fullQuiz

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.example.mr_motor_.R
import com.example.mr_motor_.data.storage.SessionManager
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.models.quiz.QuizItemVO
import com.example.mr_motor_.domain.models.quiz.QuizVO
import com.example.mr_motor_.domain.models.quiz.ResultQuiz
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizQPage : AppCompatActivity() {

    private var id_quiz : Long = 0
    private var number_of_question : Int = 0
    private lateinit var full_quiz : QuizVO
    private lateinit var list_questions : List<QuizItemVO>
    private lateinit var question : QuizItemVO
    private var right_answers : Int = 0

    private lateinit var sessionManager: SessionManager

    private lateinit var number_of_question_textView : TextView
    private lateinit var question_textView : TextView
    private lateinit var firstAnswerButton : Button
    private lateinit var secondAnswerButton : Button
    private lateinit var thirdAnswerButton : Button
    private lateinit var fourthAnswerButton : Button
    private lateinit var cardWithImage : CardView
    private lateinit var image : ImageView

    private lateinit var vLoading : View
    private lateinit var pbLoading : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.quiz_question_page)
        supportActionBar?.hide()

        sessionManager = SessionManager(this)

        number_of_question_textView = findViewById(R.id.tv_number_of_question)
        question_textView = findViewById(R.id.tv_question)
        firstAnswerButton = findViewById(R.id.btn_first_answer)
        secondAnswerButton = findViewById(R.id.btn_second_answer)
        thirdAnswerButton = findViewById(R.id.btn_third_answer)
        fourthAnswerButton = findViewById(R.id.btn_fourth_answer)
        image = findViewById(R.id.iv_quiz_background)
        cardWithImage = findViewById(R.id.cv_photo)
        vLoading = findViewById(R.id.v_loading)
        pbLoading = findViewById(R.id.pb_loading)

        buttons_enabled(false)

        firstAnswerButton.setOnClickListener {
            val correct = find_correct_answer()
            firstAnswerButton.postDelayed(Runnable { next_question() }, 1000)
            if(correct == 0){
                firstAnswerButton.setBackgroundResource(R.color.mint_green)
                right_answers++
            }
            else{
                firstAnswerButton.setBackgroundResource(R.color.red)
                when(correct){
                    1 -> secondAnswerButton.setBackgroundResource(R.color.mint_green)
                    2 -> thirdAnswerButton.setBackgroundResource(R.color.mint_green)
                    3 -> fourthAnswerButton.setBackgroundResource(R.color.mint_green)
                }

            }
        }
        secondAnswerButton.setOnClickListener {
            val correct = find_correct_answer()
            secondAnswerButton.postDelayed(Runnable { next_question() }, 1000)
            if(correct == 1){
                secondAnswerButton.setBackgroundResource(R.color.mint_green)
                right_answers++
            }
            else{
                secondAnswerButton.setBackgroundResource(R.color.red)
                when(correct){
                    0 -> firstAnswerButton.setBackgroundResource(R.color.mint_green)
                    2 -> thirdAnswerButton.setBackgroundResource(R.color.mint_green)
                    3 -> fourthAnswerButton.setBackgroundResource(R.color.mint_green)
                }
            }

        }
        thirdAnswerButton.setOnClickListener {
            val correct = find_correct_answer()
            thirdAnswerButton.postDelayed(Runnable { next_question() }, 1000)
            if(correct == 2){
                thirdAnswerButton.setBackgroundResource(R.color.mint_green)
                right_answers++
            }
            else{
                thirdAnswerButton.setBackgroundResource(R.color.red)
                when(correct){
                    0 -> firstAnswerButton.setBackgroundResource(R.color.mint_green)
                    1 -> secondAnswerButton.setBackgroundResource(R.color.mint_green)
                    3 -> fourthAnswerButton.setBackgroundResource(R.color.mint_green)
                }

            }
        }
        fourthAnswerButton.setOnClickListener {
            val correct = find_correct_answer()
            fourthAnswerButton.postDelayed(Runnable { next_question() }, 1000)
            if(correct == 3){
                fourthAnswerButton.setBackgroundResource(R.color.mint_green)
                right_answers++
            }
            else{
                fourthAnswerButton.setBackgroundResource(R.color.red)
                when(correct){
                    1 -> secondAnswerButton.setBackgroundResource(R.color.mint_green)
                    2 -> thirdAnswerButton.setBackgroundResource(R.color.mint_green)
                    0 -> firstAnswerButton.setBackgroundResource(R.color.mint_green)
                }
            }

        }

        if (intent.hasExtra(QuizQPage.QUIZ_ID)){

            start_quiz()
        }
    }

    fun start_quiz(){
        id_quiz = intent.getLongExtra(QuizQPage.QUIZ_ID, 1)

        ApiClient.getApiService().get_quiz(id_quiz, sessionManager.fetchAuthToken()).enqueue(object :
            Callback<QuizVO> {
            override fun onFailure(call: Call<QuizVO>, t: Throwable) {
                t.printStackTrace()
                Log.e("LIST_QUESTIONS_API", "doesn't work")
            }

            override fun onResponse(call: Call<QuizVO>, response: Response<QuizVO>) {
                full_quiz = response.body()!!
                list_questions = full_quiz.quizItems
                setting_first_question()
                buttons_enabled(true)

            }
        })
    }

    fun setting_first_question(){
        number_of_question = 0

        question = list_questions[number_of_question]
        set_image()
        number_of_question_textView.text = (number_of_question + 1).toString() + " out of " + list_questions.size.toString()
        number_of_question_textView.text = (number_of_question + 1).toString()
        question_textView.text = question.question
        firstAnswerButton.text = question.quizAnswers[0].answer
        secondAnswerButton.text = question.quizAnswers[1].answer
        thirdAnswerButton.text = question.quizAnswers[2].answer
        fourthAnswerButton.text = question.quizAnswers[3].answer

        vLoading.isVisible = false
        pbLoading.isVisible = false
    }

    fun next_question(){

        number_of_question++

        if(number_of_question >= list_questions.size){
            buttons_enabled(false)

            QuizResultPage.start(this, ResultQuiz(right_answers = right_answers, number_of_questions = list_questions.size, quiz_name = full_quiz.title, quiz_id = id_quiz))
            finish()
        }
        else{
            buttons_grey()

            number_of_question_textView.text = (number_of_question + 1).toString() + " out of " + list_questions.size.toString()
            question = list_questions[number_of_question]
            set_image()
            question_textView.text = question.question
            firstAnswerButton.text = question.quizAnswers[0].answer
            secondAnswerButton.text = question.quizAnswers[1].answer
            thirdAnswerButton.text = question.quizAnswers[2].answer
            fourthAnswerButton.text = question.quizAnswers[3].answer
        }
    }

    fun set_image(){
        if(question.image != ""){
            val encoded : String = question.image.substring(question.image.indexOf(',')+1)

            val decodedString: ByteArray = Base64.decode(encoded, Base64.DEFAULT)
            val bitmap =
                BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            cardWithImage.isVisible = true
            image.setImageBitmap(bitmap)
        }
        else{
            cardWithImage.isVisible = false
        }

    }

    fun find_correct_answer() : Int {
        var number = 0;
        for (i in list_questions[number_of_question].quizAnswers){
            if(i.isCorrect){
                return number
            }
            number++
        }
        return number
    }

    fun buttons_enabled(item : Boolean){
        firstAnswerButton.isEnabled = item
        secondAnswerButton.isEnabled = item
        thirdAnswerButton.isEnabled = item
        fourthAnswerButton.isEnabled = item
    }

    fun buttons_grey(){
        firstAnswerButton.setBackgroundResource(R.color.light_grey)
        secondAnswerButton.setBackgroundResource(R.color.light_grey)
        thirdAnswerButton.setBackgroundResource(R.color.light_grey)
        fourthAnswerButton.setBackgroundResource(R.color.light_grey)
    }

    companion object{
        private const val QUIZ_ID : String = "QuizQPage.QUIZ_ID"
        fun start(caller: Activity, quizID: Long? ){
            val intent : Intent = Intent(caller, QuizQPage::class.java)
            if(quizID != null){
                intent.putExtra(QUIZ_ID, quizID)
            }
            caller.startActivity(intent)
        }
    }
}