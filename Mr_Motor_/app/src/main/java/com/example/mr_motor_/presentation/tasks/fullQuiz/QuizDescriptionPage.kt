package com.example.mr_motor_.presentation.tasks.fullQuiz

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.Post
import com.example.mr_motor_.domain.models.quiz.ShortQuizVO
import com.example.mr_motor_.presentation.posts.detailsPage.NewsDetailsPage
import com.squareup.picasso.Picasso

class QuizDescriptionPage : AppCompatActivity() {

    private var quiz : ShortQuizVO? = null
    private lateinit var title : TextView
    private lateinit var description : TextView
    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.quiz_description_page)
        supportActionBar?.hide()

        title = findViewById(R.id.tv_quizDescription_title)
        description = findViewById(R.id.tv_quizDescription_description)
        button = findViewById(R.id.btn_start_quiz)

        if (intent.hasExtra(QuizDescriptionPage.QUIZ_VO)){
            quiz = intent.getParcelableExtra(QuizDescriptionPage.QUIZ_VO)
            title?.text = quiz?.title
            description?.text = quiz?.description

            button?.setOnClickListener {
                QuizQPage.start(this, quiz?.id)
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