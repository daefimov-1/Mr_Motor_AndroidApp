package com.example.mr_motor_.di

import com.example.mr_motor_.presentation.AccountSettingsPageViewModel
import com.example.mr_motor_.presentation.AuthorizationViewModel
import com.example.mr_motor_.presentation.StartAppViewModel
import com.example.mr_motor_.presentation.posts.PostsPageViewModel
import com.example.mr_motor_.presentation.posts.detailsPage.NewsDetailsPageViewModel
import com.example.mr_motor_.presentation.tasks.TaskFragmentViewModel
import com.example.mr_motor_.presentation.tasks.fullQuiz.QuizQPageViewModel
import com.example.mr_motor_.presentation.tasks.fullQuiz.QuizResultPageViewModel
import com.example.mr_motor_.presentation.tasks.myQuizes.MyQuizzesPageViewModel
import com.example.mr_motor_.presentation.tasks.myQuizes.MyQuizzesResultPageViewModel
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

    viewModel<StartAppViewModel> {
        StartAppViewModel(
            loadPostsUseCase = get()
        )
    }

    viewModel<AccountSettingsPageViewModel> {
        AccountSettingsPageViewModel(
            updateUserDataUseCase = get()
        )
    }

    viewModel<QuizQPageViewModel> {
        QuizQPageViewModel(
             getQuizUseCase = get()
        )
    }

    viewModel<QuizResultPageViewModel> {
        QuizResultPageViewModel(
            postResultUseCase = get()
        )
    }

    viewModel<MyQuizzesPageViewModel> {
        MyQuizzesPageViewModel(
            getUserQuizzesUseCase = get()
        )
    }

    viewModel<MyQuizzesResultPageViewModel> {
        MyQuizzesResultPageViewModel(
            getQuizzesResultsUseCase = get()
        )
    }

    viewModel<NewsDetailsPageViewModel> {
        NewsDetailsPageViewModel(
            likeUseCase = get()
        )
    }

    viewModel<PostsPageViewModel> {
        PostsPageViewModel(
            getPostsUseCase = get(),
            getLikedPostsUseCase = get()
        )
    }

    viewModel<TaskFragmentViewModel> {
        TaskFragmentViewModel(
            loadQuizzesUseCase = get()
        )
    }

}