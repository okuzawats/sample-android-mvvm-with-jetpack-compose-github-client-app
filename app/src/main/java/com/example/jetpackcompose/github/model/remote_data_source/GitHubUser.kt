package com.example.jetpackcompose.github.model.remote_data_source

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubUser(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("avatar_url") val avatarUrl: String,
    @SerialName("blog") val blog: String,
)