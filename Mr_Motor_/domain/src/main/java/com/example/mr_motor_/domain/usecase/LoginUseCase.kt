package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.repository.UserRepository

class LoginUseCase(private val userRepository: UserRepository) {
    fun execute(email: String, password: String, resultLiveMutable: MutableLiveData<Boolean>) {
        userRepository.login(
            email = email,
            password = password,
            resultLiveMutable = resultLiveMutable
        )
    }
}