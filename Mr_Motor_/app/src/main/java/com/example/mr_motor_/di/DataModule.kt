package com.example.mr_motor_.di

import com.example.mr_motor_.data.datasource.storage.*
import com.example.mr_motor_.data.repository.PostsRepositoryImpl
import com.example.mr_motor_.data.repository.QuizRepositoryImpl
import com.example.mr_motor_.data.repository.UserRepositoryImpl
import com.example.mr_motor_.domain.repository.PostsRepository
import com.example.mr_motor_.domain.repository.QuizRepository
import com.example.mr_motor_.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {

    single<UserStorage> {
        UserSharedPrefStorage(context = get())
    }

    single<TokenStorage> {
        TokenSharedPrefStorage(context = get())
    }

    single<PostStorage> {
        PostSharedPrefStorage(context = get())
    }

    single<PostsRepository>{
        PostsRepositoryImpl(postStorage = get(), tokenStorage = get())
    }

    single<UserRepository> {
        UserRepositoryImpl(userStorage = get(), tokenStorage = get())
    }

    single<QuizRepository> {
        QuizRepositoryImpl(tokenStorage = get())
    }
}