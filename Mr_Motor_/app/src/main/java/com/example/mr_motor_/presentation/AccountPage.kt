package com.example.mr_motor_.presentation

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.UserResponse
import com.example.mr_motor_.domain.repository.UserRepository
import com.example.mr_motor_.presentation.posts.FavouritePostsPage
import com.example.mr_motor_.presentation.tasks.myQuizes.MyQuizesPage
import com.example.mr_motor_.presentation.tasks.myQuizes.MyQuizesResultsPage
import com.google.android.material.transition.platform.MaterialSharedAxis
import org.koin.android.ext.android.inject


class AccountPage : AppCompatActivity() {

    private lateinit var buttonEdit : ImageButton
    private lateinit var avatar : ImageView
    private lateinit var favouriteButton : View
    private lateinit var myQuizzesButton : View
    private lateinit var quizzesResultsButton : View

    private val userRepository by inject<UserRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.account_page)

        // Slide right
        val sharedAxisExit = MaterialSharedAxis(MaterialSharedAxis.X, true).apply{
            excludeTarget(R.id.action_bar_container, true)
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }
        val sharedAxisEnter = MaterialSharedAxis(MaterialSharedAxis.X, false).apply{
            excludeTarget(R.id.action_bar_container, true)
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }

        window.exitTransition = sharedAxisExit
        window.enterTransition = sharedAxisEnter

        buttonEdit = findViewById(R.id.ib_edit)
        buttonEdit.setOnClickListener {
            AccountSettingsPage.start(this)
        }

        avatar = findViewById(R.id.iv_account_photo)
        favouriteButton = findViewById(R.id.v_frame_1)
        favouriteButton.setOnClickListener {
            FavouritePostsPage.start(this)
        }

        myQuizzesButton = findViewById(R.id.v_frame_2)
        myQuizzesButton.setOnClickListener {
            MyQuizesPage.start(this)
        }

        quizzesResultsButton = findViewById(R.id.v_frame_3)
        quizzesResultsButton.setOnClickListener {
            MyQuizesResultsPage.start(this)
        }

        val user : UserResponse? = userRepository.getUserData()

        if(user != null){
            findViewById<TextView>(R.id.tv_name).text = user.name
            findViewById<TextView>(R.id.tv_email).text = user.email

            if(user.avatar.isNotEmpty()){
                val encoded : String = user.avatar.substring(user.avatar.indexOf(',')+1)

                val decodedString: ByteArray = Base64.decode(encoded, Base64.DEFAULT)
                val bitmap =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                avatar.setImageBitmap(bitmap)
            }

        }
        findViewById<Button>(R.id.btn_logout).setOnClickListener {
            userRepository.deleteUserData()
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        val user : UserResponse? = userRepository.getUserData()

        if(user != null){
            findViewById<TextView>(R.id.tv_name).text = user.name
            findViewById<TextView>(R.id.tv_email).text = user.email

            if(user.avatar.isNotEmpty()){
                val encoded : String = user.avatar.substring(user.avatar.indexOf(',')+1)

                val decodedString: ByteArray = Base64.decode(encoded, Base64.DEFAULT)
                val bitmap =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                avatar.setImageBitmap(bitmap)
            }

        }
    }

    companion object{
        fun start(caller : FragmentActivity?){
            val intent : Intent = Intent(caller, AccountPage::class.java)
            caller?.startActivity(intent)
        }
    }
}