package com.example.mr_motor_.domain.models

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("avatar")
    val avatar: String
)