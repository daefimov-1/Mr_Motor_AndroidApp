package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.repository.UserRepository

class ForgotPasswordUseCase(private val userRepository: UserRepository) {

    fun execute(email: String, resultLiveMutable: MutableLiveData<Boolean>) {

        if(!isEmailValid(email)){
            resultLiveMutable.value = false
        }
        else{
            userRepository.forgotPassword(email = email, resultLiveMutable = resultLiveMutable)
        }

    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}