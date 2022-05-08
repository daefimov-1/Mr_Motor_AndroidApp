package com.example.mr_motor_.presentation.tasks.fullQuiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mr_motor_.R
import com.example.mr_motor_.data.repository.UserRepositoryImpl
import com.example.mr_motor_.data.datasource.storage.UserSharedPrefStorage
import com.example.mr_motor_.domain.models.quiz.ResultQuiz
import com.example.mr_motor_.domain.usecase.PostResultOfQuiz
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt

class QuizResultPage : AppCompatActivity() {

    private lateinit var result: ResultQuiz

    private lateinit var mConstraintLayout: ConstraintLayout
    private lateinit var percentageText: TextView
    private lateinit var nameOfQuiz: TextView
    private lateinit var amount: TextView
    private lateinit var tryAgainButton: Button

    private val vm by viewModel<QuizResultPageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.quiz_result_page)
        supportActionBar?.hide()

        mConstraintLayout = findViewById(R.id.cl_inner_constraint)
        percentageText = findViewById(R.id.tv_percentage)
        amount = findViewById(R.id.tv_result_in_numbers)
        nameOfQuiz = findViewById(R.id.tv_quizResult_title)
        tryAgainButton = findViewById(R.id.btn_try_again)

        if (intent.hasExtra(QuizResultPage.RESULT)) {
            result = intent.getParcelableExtra(QuizResultPage.RESULT)!!

            var percentage =
                ((result.right_answers.toFloat() / result.number_of_questions) * 100).roundToInt()
            percentageText.text = "$percentage%"
            amount.text = "${result.right_answers} Out Of ${result.number_of_questions}"
            nameOfQuiz.text = result.quiz_name

            (mConstraintLayout.layoutParams as ConstraintLayout.LayoutParams)
                .matchConstraintPercentWidth = percentage.toFloat() / 100
            mConstraintLayout.requestLayout()

            vm.postResult(result = result)

            tryAgainButton.setOnClickListener {
                QuizQPage.start(this, result.quiz_id)
                finish()
            }
        }
    }

    companion object {
        private const val RESULT: String = "QuizResultPage.RESULT"
        fun start(caller: Activity, result: ResultQuiz?) {
            val intent: Intent = Intent(caller, QuizResultPage::class.java)
            if (result != null) {
                intent.putExtra(RESULT, result)
            }
            caller.startActivity(intent)
        }
    }
}