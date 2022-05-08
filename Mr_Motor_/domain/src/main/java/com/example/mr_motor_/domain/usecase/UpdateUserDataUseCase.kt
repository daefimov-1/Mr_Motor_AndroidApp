package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.repository.UserRepository

class UpdateUserDataUseCase(private val userRepository: UserRepository) {

    fun execute(
        name: String,
        email: String,
        avatarString: String?,
        userAvatar: String,
        resultLiveMutable: MutableLiveData<Boolean>
    ) {
        userRepository.updateUserData(
            name = name,
            email = email,
            avatarString = avatarString,
            userAvatar = userAvatar,
            resultLiveMutable = resultLiveMutable
        )

    }
}