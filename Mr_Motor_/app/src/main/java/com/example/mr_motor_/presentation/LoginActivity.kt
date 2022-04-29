package com.example.mr_motor_.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mr_motor_.R

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var signUpButton: Button
    private lateinit var forgotPasswordButton: Button

    private lateinit var vm: AuthorizationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setTheme(R.style.splashScreenTheme)
        setContentView(R.layout.log_in_page)

        email = findViewById(R.id.et_signUpPage_email)
        password = findViewById(R.id.et_signUpPage_password)
        loginButton = findViewById(R.id.btn_log_in)
        signUpButton = findViewById(R.id.btn_sign_up)
        forgotPasswordButton = findViewById(R.id.btn_forgot_password)

        vm = ViewModelProvider(this, AuthorizationViewModelFactory(applicationContext)).get(
            AuthorizationViewModel::class.java
        )

        vm.resultLive.observe(this, Observer {
            if (it) {
                finish()
            } else {
                val toast =
                    Toast.makeText(this@LoginActivity, "Login data is incorrect", Toast.LENGTH_LONG)
                toast.show()
            }
        })

        loginButton.setOnClickListener {

            vm.login(email = email.text.toString(), password = password.text.toString())

        }

        signUpButton.setOnClickListener {
            SignUpPage.start(this)
        }

        forgotPasswordButton.setOnClickListener {
            ForgotPasswordPage.start(this)
        }

    }

    companion object {

        fun start(caller: FragmentActivity?) {
            val intent: Intent = Intent(caller, LoginActivity::class.java)
            caller?.startActivity(intent)
        }
    }

}