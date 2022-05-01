package com.example.mr_motor_.di

import com.example.mr_motor_.presentation.AuthorizationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<AuthorizationViewModel> {
        AuthorizationViewModel(
            loginUseCase = get(),
            signUpUseCase = get(),
            forgotPasswordUseCase = get()
        )
    }
}