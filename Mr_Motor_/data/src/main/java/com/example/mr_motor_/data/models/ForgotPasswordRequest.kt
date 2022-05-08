package com.example.mr_motor_.data.models

import com.google.gson.annotations.SerializedName

data class ForgotPasswordRequest(
    @SerializedName("email")
    var email: String
)
