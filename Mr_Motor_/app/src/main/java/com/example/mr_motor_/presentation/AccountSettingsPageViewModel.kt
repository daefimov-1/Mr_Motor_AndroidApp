package com.example.mr_motor_.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mr_motor_.domain.usecase.UpdateUserDataUseCase

class AccountSettingsPageViewModel(
    private val updateUserDataUseCase : UpdateUserDataUseCase
) : ViewModel() {

    private val resultLiveMutable = MutableLiveData<Boolean>()
    val resultLive : LiveData<Boolean> = resultLiveMutable

    fun updateData(name : String, email : String, avatarString : String?, userAvatar : String){
        updateUserDataUseCase.execute(
            name = name,
            email = email,
            avatarString = avatarString,
            userAvatar = userAvatar,
            resultLiveMutable = resultLiveMutable
        )
    }
}