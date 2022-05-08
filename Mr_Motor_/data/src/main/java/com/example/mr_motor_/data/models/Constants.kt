package com.example.mr_motor_.data.models

object Constants {

    // Endpoints
    const val BASE_URL = "https://mrmotor.herokuapp.com/"
    const val LOGIN_URL = "ulogin"
    const val NEWS_URL = "posts/news_limit?offset=0&limit=10"
    const val DETAILS_URL = "users/details"
    const val FORGOTPASSWORD_URL = "users/forgot_password"
    const val SIGNUP_URL = "users/registrations"
    const val UPDATE_URL = "users"
    const val RACERS_URL = "posts/get_by_type_limit?type=RACER&offset=0&limit=10"
    const val COMPETITIONS_URL = "posts/get_by_type_limit?type=COMPETITION&offset=0&limit=10"
    const val CARS_URL = "posts/get_by_type_limit?type=CAR&offset=0&limit=10"
    const val QUIZ_URL = "quiz"
}