package com.example.mr_motor_.domain.models

import com.google.gson.annotations.SerializedName

data class ForgotPasswordRequest(
    @SerializedName("email")
    var email: String
)
