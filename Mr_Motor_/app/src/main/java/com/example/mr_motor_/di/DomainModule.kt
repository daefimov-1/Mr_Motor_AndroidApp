package com.example.mr_motor_.di

import com.example.mr_motor_.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {

    factory<ForgotPasswordUseCase> {
        ForgotPasswordUseCase(userRepository = get())
    }

    factory<GetLikedPostsUseCase> {
        GetLikedPostsUseCase(postsRepository = get())
    }

    factory<GetPostsUseCase>{
        GetPostsUseCase(postsRepository = get())
    }

    factory<GetQuizUseCase>{
        GetQuizUseCase(quizRepository = get())
    }

    factory<GetQuizzesResultsUseCase>{
        GetQuizzesResultsUseCase(quizRepository = get())
    }

    factory<GetUserQuizzesUseCase>{
        GetUserQuizzesUseCase(quizRepository = get())
    }

    factory<LikeUseCase>{
        LikeUseCase(postsRepository = get())
    }

    factory<LoadPostsUseCase>{
        LoadPostsUseCase(postsRepository = get())
    }

    factory<LoadQuizzesUseCase> {
        LoadQuizzesUseCase(quizRepository = get())
    }

    factory<LoginUseCase> {
        LoginUseCase(userRepository = get())
    }

    factory<PostResultOfQuiz>{
        PostResultOfQuiz(quizRepository = get())
    }

    factory<SignUpUseCase> {
        SignUpUseCase(userRepository = get())
    }

    factory<UpdateUserDataUseCase> {
        UpdateUserDataUseCase(userRepository = get())
    }


}