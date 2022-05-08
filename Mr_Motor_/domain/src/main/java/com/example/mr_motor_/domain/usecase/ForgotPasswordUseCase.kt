package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.repository.UserRepository

class ForgotPasswordUseCase(private val userRepository: UserRepository) {

    fun execute(email: String, resultLiveMutable: MutableLiveData<Boolean>) {
        userRepository.forgotPassword(email = email, resultLiveMutable = resultLiveMutable)
    }
}