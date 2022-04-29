package com.example.mr_motor_.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mr_motor_.data.repository.UserRepositoryImpl
import com.example.mr_motor_.data.storage.UserSharedPrefStorage
import com.example.mr_motor_.domain.models.ResponseCallback
import com.example.mr_motor_.domain.usecase.ForgotPasswordUseCase
import com.example.mr_motor_.domain.usecase.LoginUseCase
import com.example.mr_motor_.domain.usecase.SignUpUseCase

class AuthorizationViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val userStorage by lazy { UserSharedPrefStorage(context = context) }
    private val userRepository by lazy { UserRepositoryImpl(userStorage = userStorage) }

    private val loginUseCase by lazy { LoginUseCase(userRepository = userRepository) }
    private val signUpUseCase by lazy { SignUpUseCase() }
    private val forgotPasswordUseCase by lazy { ForgotPasswordUseCase() }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthorizationViewModel(
            loginUseCase = loginUseCase,
            signUpUseCase = signUpUseCase,
            forgotPasswordUseCase = forgotPasswordUseCase
        ) as T
    }
}