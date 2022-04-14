package com.example.mr_motor_.presentation.tasks.fullQuiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.quiz.ResultQuiz
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO
import kotlin.math.roundToInt

class QuizResultPage : AppCompatActivity() {

    private lateinit var result : ResultQuiz

    private lateinit var mConstraintLayout : ConstraintLayout
    private lateinit var percentage_text : TextView
    private lateinit var name_of_quiz : TextView
    private lateinit var amount : TextView
    private lateinit var try_again_btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.quiz_result_page)
        supportActionBar?.hide()

        mConstraintLayout = findViewById(R.id.cl_inner_constraint)
        percentage_text = findViewById(R.id.tv_percentage)
        amount = findViewById(R.id.tv_result_in_numbers)
        name_of_quiz = findViewById(R.id.tv_quizResult_title)
        try_again_btn = findViewById(R.id.btn_try_again)

        if (intent.hasExtra(QuizResultPage.RESULT)){
            result = intent.getParcelableExtra(QuizResultPage.RESULT)!!

            var percentage = ((result.right_answers.toFloat() / result.number_of_questions) * 100).roundToInt()
            percentage_text.text = "$percentage%"
            amount.text = "${result.right_answers} Out Of ${result.number_of_questions}"
            name_of_quiz.text = result.quiz_name

            (mConstraintLayout.layoutParams as ConstraintLayout.LayoutParams)
                .matchConstraintPercentWidth = percentage.toFloat() /100
            mConstraintLayout.requestLayout()

            try_again_btn.setOnClickListener {
                QuizQPage.start(this, result.quiz_id)
                finish()
            }
        }
    }

    companion object{
        private const val RESULT : String = "QuizResultPage.RESULT"
        fun start(caller: Activity, result: ResultQuiz? ){
            val intent : Intent = Intent(caller, QuizResultPage::class.java)
            if(result != null){
                intent.putExtra(RESULT, result)
            }
            caller.startActivity(intent)
        }
    }
}