package com.example.jetpackcompose.github.model.repository

/**
 * ユーザーを表すクラス
 */
data class User(
    val userId: UserId,
    val name: String,
    val avatarImage: NetworkImage,
    val blogUrl: Url,
)