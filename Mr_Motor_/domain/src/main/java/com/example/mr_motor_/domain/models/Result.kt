package com.example.mr_motor_.domain.models

sealed class Result
data class ValidResult(val result: List<Post>) : Result()
object EmptyResult : Result()
object EmptyQuery : Result()
data class ErrorResult(val e: Throwable) : Result()
object TerminalError : Result()