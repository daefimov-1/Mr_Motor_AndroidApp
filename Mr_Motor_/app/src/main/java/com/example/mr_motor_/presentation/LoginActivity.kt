package com.example.mr_motor_.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentActivity
import com.example.mr_motor_.R
import com.example.mr_motor_.data.repository.UserRepositoryImpl
import com.example.mr_motor_.data.storage.UserSharedPrefStorage
import com.example.mr_motor_.domain.models.ResponseCallback
import com.example.mr_motor_.domain.usecase.LoginUseCase

class LoginActivity : AppCompatActivity(), ResponseCallback {

    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var login_button : Button
    private lateinit var signUp_button : Button
    private lateinit var forgotPassword_button : Button

    private val userStorage by lazy { UserSharedPrefStorage(context = this) }
    private val userRepository by lazy { UserRepositoryImpl(userStorage = userStorage) }
    private val loginUseCase by lazy { LoginUseCase(userRepository = userRepository, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setTheme(R.style.splashScreenTheme)
        setContentView(R.layout.log_in_page)

        email = findViewById(R.id.et_signUpPage_email)
        password = findViewById(R.id.et_signUpPage_password)
        login_button = findViewById(R.id.btn_log_in)
        signUp_button = findViewById(R.id.btn_sign_up)
        forgotPassword_button = findViewById(R.id.btn_forgot_password)


        login_button.setOnClickListener {

            if(email.text.isNotEmpty() && password.text.isNotEmpty()){

                loginUseCase.execute(email = email.text.toString(), password = password.text.toString())

            }

        }

        signUp_button.setOnClickListener {
            SignUpPage.start(this)
        }

        forgotPassword_button.setOnClickListener {
            ForgotPasswordPage.start(this)
        }

    }

    override fun response(result : Boolean) {
        if(result){
            finish()
        }
        else{
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        }
    }

    companion object{

        fun start(caller: FragmentActivity?){
            val intent : Intent = Intent(caller, LoginActivity::class.java)
            caller?.startActivity(intent)
        }
    }

}