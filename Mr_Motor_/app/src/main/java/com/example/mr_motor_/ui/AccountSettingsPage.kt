package com.example.mr_motor_.ui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mr_motor_.R
import com.example.mr_motor_.login.ApiClient
import com.example.mr_motor_.login.SessionManager
import com.example.mr_motor_.models.SignUpRequest
import com.example.mr_motor_.models.UserResponse
import com.google.android.material.transition.platform.MaterialSharedAxis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AccountSettingsPage : AppCompatActivity() {

    private lateinit var nameEditText : EditText
    private lateinit var saveButton : ImageButton

    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.account_settings_page)

        apiClient = ApiClient()

        nameEditText = findViewById(R.id.et_settingsPage_name)
        saveButton = findViewById(R.id.ib_settingsPage_save)

        val sessionManager : SessionManager = SessionManager(this)
        val user : UserResponse? = sessionManager.fetchUser()

        if(user != null){
            findViewById<TextView>(R.id.tv_name).text = user.name
            findViewById<TextView>(R.id.tv_email).text = user.email
            nameEditText.setText(user.name)
        }

        saveButton.setOnClickListener {
            if(nameEditText.text.toString() != user?.name && user != null){

                apiClient.getApiService().update(
                    SignUpRequest(
                        name = nameEditText.text.toString(),
                        email = user.email,
                        password = "",
                        avatar = ""
                    ), sessionManager.fetchAuthToken()
                ).enqueue(object : Callback<UserResponse> {
                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<UserResponse>,
                        response: Response<UserResponse>
                    ) {
                        sessionManager.saveUser(response.body()!!)
                        var toast : Toast = Toast.makeText(this@AccountSettingsPage, "Successfully changed!", Toast.LENGTH_LONG)
                        toast.show()
                        finish()
                    }
                })
            }
        }

        val sharedAxisExit = MaterialSharedAxis(MaterialSharedAxis.X, false).apply{
            excludeTarget(R.id.action_bar_container, true)
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }
        val sharedAxisEnter = MaterialSharedAxis(MaterialSharedAxis.X, true).apply{
            excludeTarget(R.id.action_bar_container, true)
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }

        window.exitTransition = sharedAxisExit
        window.enterTransition = sharedAxisEnter

    }

    companion object{
        fun start(caller : Activity){
            val intent = Intent(caller, AccountSettingsPage::class.java)
            val activityOptions = ActivityOptions.makeSceneTransitionAnimation(caller)
            caller.startActivity(intent, activityOptions.toBundle())
        }
    }
}