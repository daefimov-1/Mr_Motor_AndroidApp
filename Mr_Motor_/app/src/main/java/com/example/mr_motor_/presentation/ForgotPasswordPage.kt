package com.example.mr_motor_.presentation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.ResponseCallback
import com.example.mr_motor_.domain.usecase.ForgotPasswordUseCase

class ForgotPasswordPage : AppCompatActivity(), ResponseCallback {

    private lateinit var email : EditText
    private lateinit var resetPassword : Button

    private val forgotPasswordUseCase by lazy {
        ForgotPasswordUseCase(
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.forgot_password_page)

        email = findViewById(R.id.et_forgotPasswordPage_email)
        resetPassword = findViewById(R.id.btn_reset_password)

        resetPassword.setOnClickListener {
            if(email.text.isNotEmpty()){

                forgotPasswordUseCase.execute(email.text.toString())

            }
        }
    }

    companion object{
        fun start(caller : Activity){
            val intent = Intent(caller, ForgotPasswordPage::class.java)
            caller.startActivity(intent)
        }
    }

    override fun response(result: Boolean) {
        val toast : Toast
        if(result){
            toast = Toast.makeText(this@ForgotPasswordPage, "Letter is send on your email", Toast.LENGTH_LONG)
            toast.show()
            finish()
        }else{
            toast = Toast.makeText(this@ForgotPasswordPage, "No account on such email", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}