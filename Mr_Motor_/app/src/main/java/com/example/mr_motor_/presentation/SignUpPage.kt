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
import com.example.mr_motor_.domain.usecase.SignUpUseCase

class SignUpPage : AppCompatActivity(), ResponseCallback {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signUpButton: Button

    private val signUpUseCase by lazy { SignUpUseCase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.sign_up_page)

        name = findViewById(R.id.et_signUpPage_name)
        email = findViewById(R.id.et_signUpPage_email)
        password = findViewById(R.id.et_signUpPage_password)
        signUpButton = findViewById(R.id.btn_sign_up)

        signUpButton.setOnClickListener {
            if (name.text.isNotEmpty() && email.text.isNotEmpty() && password.text.isNotEmpty()) {

                signUpUseCase.execute(name = name.text.toString(), email = email.text.toString(), password = password.text.toString())
            }
        }
    }

    companion object {
        fun start(caller: Activity) {
            val intent = Intent(caller, SignUpPage::class.java)
            caller.startActivity(intent)
        }
    }

    override fun response(result : Boolean) {
        val toast : Toast
        if(result){
            toast = Toast.makeText(this@SignUpPage, "Successfully signed up!", Toast.LENGTH_LONG)
            toast.show()
            finish()
        }
        else{
            toast = Toast.makeText(this@SignUpPage, "Not successfully signed up!", Toast.LENGTH_LONG)
            toast.show()
        }

    }
}