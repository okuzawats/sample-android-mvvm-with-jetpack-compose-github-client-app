package com.example.jetpackcompose.github.model.repository

data class User(
    val id: Long,
    val name: String,
    val avatarImage: NetworkImage,
    val blogUrl: Url,
)