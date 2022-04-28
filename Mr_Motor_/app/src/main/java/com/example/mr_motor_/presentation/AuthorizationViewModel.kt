package com.example.mr_motor_.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mr_motor_.domain.usecase.ForgotPasswordUseCase
import com.example.mr_motor_.domain.usecase.LoginUseCase
import com.example.mr_motor_.domain.usecase.SignUpUseCase

class AuthorizationViewModel(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {

    init {
        Log.e("VIEW_MODEL", "VM created")
    }

    override fun onCleared() {
        Log.e("VIEW_MODEL", "VM cleared")
        super.onCleared()
    }

    fun login(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            loginUseCase.execute(email = email, password = password)
        }
    }

    fun signUp(name: String, email: String, password: String) {
        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            signUpUseCase.execute(name = name, email = email, password = password)
        }
    }

    fun resetPassword(email: String) {
        if (email.isNotEmpty()) {
            forgotPasswordUseCase.execute(email = email)
        }
    }
}