package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.repository.UserRepository
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginUseCase(private val userRepository: UserRepository) {
    fun execute(email: String, password: String, resultLiveMutable: MutableLiveData<Boolean>) {

        if(!isEmailValid(email = email)){
            resultLiveMutable.value = false
        }
        else if(!isValidPassword(password = password)){
            resultLiveMutable.value = false
        }
        else{
            userRepository.login(
                email = email,
                password = password,
                resultLiveMutable = resultLiveMutable
            )
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: String?): Boolean {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
        val pattern: Pattern = Pattern.compile(passwordPattern)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }
}