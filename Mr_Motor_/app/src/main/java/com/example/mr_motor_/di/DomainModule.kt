package com.example.mr_motor_.di

import com.example.mr_motor_.domain.usecase.ForgotPasswordUseCase
import com.example.mr_motor_.domain.usecase.LoginUseCase
import com.example.mr_motor_.domain.usecase.SignUpUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<LoginUseCase> {
        LoginUseCase(userRepository = get())
    }

    factory<SignUpUseCase> {
        SignUpUseCase()
    }

    factory<ForgotPasswordUseCase> {
        ForgotPasswordUseCase()
    }
}