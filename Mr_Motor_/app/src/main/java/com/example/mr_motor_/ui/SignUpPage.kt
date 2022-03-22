package com.example.mr_motor_.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mr_motor_.R
import com.example.mr_motor_.login.ApiClient
import com.example.mr_motor_.models.ForgotPasswordRequest
import com.example.mr_motor_.models.SignUpRequest
import com.example.mr_motor_.models.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpPage : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signUpButton: Button

    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.sign_up_page)

        name = findViewById(R.id.et_signUpPage_name)
        email = findViewById(R.id.et_signUpPage_email)
        password = findViewById(R.id.et_signUpPage_password)
        signUpButton = findViewById(R.id.btn_sign_up)

        apiClient = ApiClient()

        signUpButton.setOnClickListener {
            if (name.text.isNotEmpty() && email.text.isNotEmpty() && password.text.isNotEmpty()) {

                apiClient.getApiService().signUp(
                    SignUpRequest(
                        name = name.text.toString(),
                        email = email.text.toString(),
                        password = password.text.toString(),
                        avatar = ""
                    )
                ).enqueue(object : Callback<UserResponse> {
                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<UserResponse>,
                        response: Response<UserResponse>
                    ) {
                        var toast : Toast = Toast.makeText(this@SignUpPage, "Successfully signed up!", Toast.LENGTH_LONG)
                        toast.show()
                        finish()
                    }
                })
            }
        }
    }

    companion object {
        fun start(caller: Activity) {
            var intent = Intent(caller, SignUpPage::class.java)
            caller.startActivity(intent)
        }
    }
}