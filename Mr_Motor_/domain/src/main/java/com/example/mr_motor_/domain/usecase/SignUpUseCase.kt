package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.repository.UserRepository

class SignUpUseCase(private val userRepository: UserRepository) {
    fun execute(
        name: String,
        email: String,
        password: String,
        resultLiveMutable: MutableLiveData<Boolean>
    ) {
        userRepository.signUp(
            name = name,
            email = email,
            password = password,
            resultLiveMutable = resultLiveMutable
        )
    }
}