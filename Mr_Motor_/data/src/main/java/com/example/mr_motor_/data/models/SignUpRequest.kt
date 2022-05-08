package com.example.mr_motor_.data.models

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("email")
    var email: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("avatar")
    var avatar: String,
    @SerializedName("password")
    var password: String
)