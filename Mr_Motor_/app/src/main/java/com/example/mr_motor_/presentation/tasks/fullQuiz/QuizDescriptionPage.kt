package com.example.mr_motor_.presentation.tasks.fullQuiz

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO
import com.example.mr_motor_.presentation.posts.detailsPage.NewsDetailsPage
import com.squareup.picasso.Picasso

class QuizDescriptionPage : AppCompatActivity() {

    private lateinit var quiz : ShortQuizVO
    private lateinit var title : TextView
    private lateinit var description : TextView
    private lateinit var button : Button
    private lateinit var image : ImageView
    private lateinit var view : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.quiz_description_page)
        supportActionBar?.hide()

        title = findViewById(R.id.tv_quizDescription_title)
        description = findViewById(R.id.tv_quizDescription_description)
        button = findViewById(R.id.btn_start_quiz)
        image = findViewById(R.id.iv_image)
        view = findViewById(R.id.v_frame_in_description)

        if (intent.hasExtra(QuizDescriptionPage.QUIZ_VO)){
            quiz = intent.getParcelableExtra(QuizDescriptionPage.QUIZ_VO)!!
            title.text = quiz.title
            description.text = quiz.description

            button.setOnClickListener {
                QuizQPage.start(this, quiz.id)
            }

            when {
                (quiz.id % 3) == (0).toLong() -> {
                    image.setImageResource(R.drawable.sport_car)
                }
                (quiz.id % 3) == (1).toLong() -> {
                    image.setImageResource(R.drawable.ic_racer)
                }
                (quiz.id % 3) == (2).toLong() -> {
                    image.setImageResource(R.drawable.ic_cup)
                }
            }
            when {
                (quiz.id % 5) == (0).toLong() -> {
                    view.setBackgroundResource(R.color.red)
                }
                (quiz.id % 5) == (1).toLong() -> {
                    view.setBackgroundResource(R.color.mint_green)
                }
                (quiz.id % 5) == (2).toLong() -> {
                    view.setBackgroundResource(R.color.light_blue)
                }
                (quiz.id % 5) == (3).toLong() -> {
                    view.setBackgroundResource(R.color.purple)
                }
                (quiz.id % 5) == (4).toLong() -> {
                    view.setBackgroundResource(R.color.light_orange)
                }
            }

        }
    }

    companion object{
        private const val QUIZ_VO : String = "NewsDetailsActivity.QUIZ_VO"
        fun start(caller: Activity, quizVO: ShortQuizVO? ){
            val intent : Intent = Intent(caller, QuizDescriptionPage::class.java)
            if(quizVO != null){
                intent.putExtra(QUIZ_VO, quizVO)
            }
            caller.startActivity(intent)
        }
    }
}