package com.example.mr_motor_.presentation.tasks.fullQuiz

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.example.mr_motor_.R
import com.example.mr_motor_.data.repository.UserRepositoryImpl
import com.example.mr_motor_.data.storage.UserSharedPrefStorage
import com.example.mr_motor_.domain.models.QuizCallback
import com.example.mr_motor_.domain.models.quiz.QuizItemVO
import com.example.mr_motor_.domain.models.quiz.QuizVO
import com.example.mr_motor_.domain.models.quiz.ResultQuiz
import com.example.mr_motor_.domain.usecase.GetQuizUseCase

class QuizQPage : AppCompatActivity(), QuizCallback {

    private var id_quiz: Long = 0
    private var number_of_question: Int = 0
    private lateinit var full_quiz: QuizVO
    private lateinit var list_questions: List<QuizItemVO>
    private lateinit var question: QuizItemVO
    private var right_answers: Int = 0

    private lateinit var numberOfQuestionTextView: TextView
    private lateinit var questionTextView: TextView
    private lateinit var firstAnswerButton: Button
    private lateinit var secondAnswerButton: Button
    private lateinit var thirdAnswerButton: Button
    private lateinit var fourthAnswerButton: Button
    private lateinit var cardWithImage: CardView
    private lateinit var image: ImageView

    private lateinit var vLoading: View
    private lateinit var pbLoading: ProgressBar

    private val userStorage by lazy { UserSharedPrefStorage(context = applicationContext) }
    private val userRepository by lazy { UserRepositoryImpl(userStorage = userStorage) }
    private val getQuizUseCase by lazy { GetQuizUseCase(userRepository = userRepository, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.quiz_question_page)
        supportActionBar?.hide()

        numberOfQuestionTextView = findViewById(R.id.tv_number_of_question)
        questionTextView = findViewById(R.id.tv_question)
        firstAnswerButton = findViewById(R.id.btn_first_answer)
        secondAnswerButton = findViewById(R.id.btn_second_answer)
        thirdAnswerButton = findViewById(R.id.btn_third_answer)
        fourthAnswerButton = findViewById(R.id.btn_fourth_answer)
        image = findViewById(R.id.iv_quiz_background)
        cardWithImage = findViewById(R.id.cv_photo)
        vLoading = findViewById(R.id.v_loading)
        pbLoading = findViewById(R.id.pb_loading)

        buttonsEnabled(false)

        firstAnswerButton.setOnClickListener {
            val correct = findCorrectAnswer()
            firstAnswerButton.postDelayed(Runnable { nextQuestion() }, 1000)
            if (correct == 0) {
                firstAnswerButton.setBackgroundResource(R.color.mint_green)
                right_answers++
            } else {
                firstAnswerButton.setBackgroundResource(R.color.red)
                when (correct) {
                    1 -> secondAnswerButton.setBackgroundResource(R.color.mint_green)
                    2 -> thirdAnswerButton.setBackgroundResource(R.color.mint_green)
                    3 -> fourthAnswerButton.setBackgroundResource(R.color.mint_green)
                }

            }
        }
        secondAnswerButton.setOnClickListener {
            val correct = findCorrectAnswer()
            secondAnswerButton.postDelayed(Runnable { nextQuestion() }, 1000)
            if (correct == 1) {
                secondAnswerButton.setBackgroundResource(R.color.mint_green)
                right_answers++
            } else {
                secondAnswerButton.setBackgroundResource(R.color.red)
                when (correct) {
                    0 -> firstAnswerButton.setBackgroundResource(R.color.mint_green)
                    2 -> thirdAnswerButton.setBackgroundResource(R.color.mint_green)
                    3 -> fourthAnswerButton.setBackgroundResource(R.color.mint_green)
                }
            }

        }
        thirdAnswerButton.setOnClickListener {
            val correct = findCorrectAnswer()
            thirdAnswerButton.postDelayed(Runnable { nextQuestion() }, 1000)
            if (correct == 2) {
                thirdAnswerButton.setBackgroundResource(R.color.mint_green)
                right_answers++
            } else {
                thirdAnswerButton.setBackgroundResource(R.color.red)
                when (correct) {
                    0 -> firstAnswerButton.setBackgroundResource(R.color.mint_green)
                    1 -> secondAnswerButton.setBackgroundResource(R.color.mint_green)
                    3 -> fourthAnswerButton.setBackgroundResource(R.color.mint_green)
                }

            }
        }
        fourthAnswerButton.setOnClickListener {
            val correct = findCorrectAnswer()
            fourthAnswerButton.postDelayed(Runnable { nextQuestion() }, 1000)
            if (correct == 3) {
                fourthAnswerButton.setBackgroundResource(R.color.mint_green)
                right_answers++
            } else {
                fourthAnswerButton.setBackgroundResource(R.color.red)
                when (correct) {
                    1 -> secondAnswerButton.setBackgroundResource(R.color.mint_green)
                    2 -> thirdAnswerButton.setBackgroundResource(R.color.mint_green)
                    0 -> firstAnswerButton.setBackgroundResource(R.color.mint_green)
                }
            }

        }

        if (intent.hasExtra(QuizQPage.QUIZ_ID)) {

            startQuiz()
        }
    }

    private fun startQuiz() {
        id_quiz = intent.getLongExtra(QuizQPage.QUIZ_ID, 1)

        getQuizUseCase.execute(id_quiz = id_quiz)
    }

    override fun response(result: QuizVO?) {
        if(result != null){
            full_quiz = result
            list_questions = full_quiz.quizItems
            settingFirstQuestion()
            buttonsEnabled(true)
        }
    }

    private fun settingFirstQuestion() {
        number_of_question = 0

        question = list_questions[number_of_question]
        setImage()
        numberOfQuestionTextView.text =
            (number_of_question + 1).toString() + " out of " + list_questions.size.toString()
        questionTextView.text = question.question
        firstAnswerButton.text = question.quizAnswers[0].answer
        secondAnswerButton.text = question.quizAnswers[1].answer
        thirdAnswerButton.text = question.quizAnswers[2].answer
        fourthAnswerButton.text = question.quizAnswers[3].answer

        vLoading.isVisible = false
        pbLoading.isVisible = false
    }

    fun nextQuestion() {

        number_of_question++

        if (number_of_question >= list_questions.size) {
            buttonsEnabled(false)

            QuizResultPage.start(
                this,
                ResultQuiz(
                    right_answers = right_answers,
                    number_of_questions = list_questions.size,
                    quiz_name = full_quiz.title,
                    quiz_id = id_quiz
                )
            )
            finish()
        } else {
            buttonsGrey()

            numberOfQuestionTextView.text =
                (number_of_question + 1).toString() + " out of " + list_questions.size.toString()
            question = list_questions[number_of_question]
            setImage()
            questionTextView.text = question.question
            firstAnswerButton.text = question.quizAnswers[0].answer
            secondAnswerButton.text = question.quizAnswers[1].answer
            thirdAnswerButton.text = question.quizAnswers[2].answer
            fourthAnswerButton.text = question.quizAnswers[3].answer
        }
    }

    private fun setImage() {
        if (question.image != "") {
            val encoded: String = question.image.substring(question.image.indexOf(',') + 1)

            val decodedString: ByteArray = Base64.decode(encoded, Base64.DEFAULT)
            val bitmap =
                BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            cardWithImage.isVisible = true
            image.setImageBitmap(bitmap)
        } else {
            cardWithImage.isVisible = false
        }

    }

    private fun findCorrectAnswer(): Int {
        var number = 0;
        for (i in list_questions[number_of_question].quizAnswers) {
            if (i.isCorrect) {
                return number
            }
            number++
        }
        return number
    }

    private fun buttonsEnabled(item: Boolean) {
        firstAnswerButton.isEnabled = item
        secondAnswerButton.isEnabled = item
        thirdAnswerButton.isEnabled = item
        fourthAnswerButton.isEnabled = item
    }

    private fun buttonsGrey() {
        firstAnswerButton.setBackgroundResource(R.color.light_grey)
        secondAnswerButton.setBackgroundResource(R.color.light_grey)
        thirdAnswerButton.setBackgroundResource(R.color.light_grey)
        fourthAnswerButton.setBackgroundResource(R.color.light_grey)
    }

    companion object {
        private const val QUIZ_ID: String = "QuizQPage.QUIZ_ID"
        fun start(caller: Activity, quizID: Long?) {
            val intent: Intent = Intent(caller, QuizQPage::class.java)
            if (quizID != null) {
                intent.putExtra(QUIZ_ID, quizID)
            }
            caller.startActivity(intent)
        }
    }
}