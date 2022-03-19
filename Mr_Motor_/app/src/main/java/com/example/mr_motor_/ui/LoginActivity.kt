package com.example.mr_motor_.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.mr_motor_.R
import com.example.mr_motor_.login.ApiClient
import com.example.mr_motor_.login.SessionManager
import com.example.mr_motor_.models.LoginRequest
import com.example.mr_motor_.models.LoginResponse
import com.example.mr_motor_.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var login_button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setTheme(R.style.splashScreenTheme)
        setContentView(R.layout.log_in_page)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        email = findViewById(R.id.et_signUpPage_email)
        password = findViewById(R.id.et_signUpPage_password)
        login_button = findViewById(R.id.btn_log_in)

        login_button.setOnClickListener {
            if(email.text.isNotEmpty() && password.text.isNotEmpty()){

                apiClient.getApiService().login(LoginRequest(email = email.text.toString(), password = password.text.toString()))
                    .enqueue(object : Callback<LoginResponse> {
                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            // Error logging in
                        }

                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                            val loginResponse = response.body()

                            sessionManager.saveAuthToken(loginResponse!!.token)

                            Log.e("USER_LOGED_IN", sessionManager.fetchAuthToken().toString())

                            takeUserDetails()
                        }
                    })


            }
        }



    }

    fun takeUserDetails(){
        apiClient.getApiService().get_details(sessionManager.fetchAuthToken().toString()).enqueue(object : Callback<UserResponse>{
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                // Error
            }

            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                val userResponse = response.body()

                if (userResponse != null) {
                    sessionManager.saveUser(userResponse)
                    Log.e("USER_NAME", userResponse.name)
                }

            }
        })
    }
}