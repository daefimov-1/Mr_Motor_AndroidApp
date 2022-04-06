package com.example.mr_motor_.presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.domain.models.ForgotPasswordRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordPage : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var resetPassword : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.forgot_password_page)

        email = findViewById(R.id.et_forgotPasswordPage_email)
        resetPassword = findViewById(R.id.btn_reset_password)

        resetPassword.setOnClickListener {
            if(email.text.isNotEmpty()){

                ApiClient.getApiService().forgotPassword(ForgotPasswordRequest(email.text.toString())).enqueue(object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        t.printStackTrace()
                        var toast : Toast = Toast.makeText(this@ForgotPasswordPage, "NOOOO", Toast.LENGTH_LONG)
                        toast.show()
                        finish()
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        var toast : Toast = Toast.makeText(this@ForgotPasswordPage, "YEsss", Toast.LENGTH_LONG)
                        toast.show()
                    }
                })

            }
        }
    }

    companion object{
        fun start(caller : Activity){
            var intent = Intent(caller, ForgotPasswordPage::class.java)
            caller.startActivity(intent)
        }
    }
}