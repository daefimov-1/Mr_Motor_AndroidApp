package com.example.mr_motor_.domain.models

sealed class Result
data class ValidResult(val result: List<Post>) : Result()
object Success : Result()
data class ErrorResult(val message: String) : Result()
object TerminalError : Result()