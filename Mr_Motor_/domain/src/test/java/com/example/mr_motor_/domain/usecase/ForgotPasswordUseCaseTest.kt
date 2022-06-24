package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.repository.UserRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(23), manifest = "src/main/AndroidManifest.xml", packageName = "com.example.mr_motor_")
class ForgotPasswordUseCaseTest {
    private val userRepository = mock<UserRepository>()
    private lateinit var resultLiveMutable : MutableLiveData<Boolean>

    @Before
    fun doBefore(){
        resultLiveMutable = MutableLiveData<Boolean>()
        Mockito.`when`(userRepository.forgotPassword(email = "email@gmail.com", resultLiveMutable = resultLiveMutable)).then {
            changeResult(true)
        }
    }

    @Test
    fun emailShouldNotBeValid(){
        val useCase = ForgotPasswordUseCase(userRepository = userRepository)
        useCase.execute(email = "abc", resultLiveMutable = resultLiveMutable)
        val expected = false
        Assert.assertEquals(expected, resultLiveMutable.value)
    }

    @Test
    fun shouldRecover(){
        val useCase = ForgotPasswordUseCase(userRepository = userRepository)
        useCase.execute(email = "email@gmail.com", resultLiveMutable = resultLiveMutable)
        val expected = true
        Assert.assertEquals(expected, resultLiveMutable.value)
    }

    private fun changeResult(bool : Boolean){
        resultLiveMutable.value = bool
    }
}