package com.example.mr_motor_.presentation

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.mr_motor_.R
import com.example.mr_motor_.data.sharedPref.SessionManager
import com.example.mr_motor_.domain.models.UserResponse
import com.google.android.material.transition.platform.MaterialSharedAxis


class AccountPage : AppCompatActivity() {

    private lateinit var buttonEdit : ImageButton
    private lateinit var avatar : ImageView

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

        val sessionManager : SessionManager = SessionManager(this)
        val user : UserResponse? = sessionManager.fetchUser()

        Log.e("USER", user.toString())

        if(user != null){
            findViewById<TextView>(R.id.tv_name).text = user.name
            findViewById<TextView>(R.id.tv_email).text = user.email

            if(user.avatar.isNotEmpty()){
                var encoded : String = user.avatar.substring(user.avatar.indexOf(',')+1)

                val decodedString: ByteArray = Base64.decode(encoded, Base64.DEFAULT)
                val bitmap =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                avatar.setImageBitmap(bitmap)
            }

        }
        findViewById<Button>(R.id.btn_logout).setOnClickListener {
            sessionManager.deleteUserData()
            finish()
        }
    }

    override fun onResume() {
        super.onResume()

        val sessionManager : SessionManager = SessionManager(this)
        val user : UserResponse? = sessionManager.fetchUser()

        if(user != null){
            findViewById<TextView>(R.id.tv_name).text = user.name
            findViewById<TextView>(R.id.tv_email).text = user.email

            if(user.avatar.isNotEmpty()){
                var encoded : String = user.avatar.substring(user.avatar.indexOf(',')+1)

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