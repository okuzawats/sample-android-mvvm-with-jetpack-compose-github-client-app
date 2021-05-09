package com.example.jetpackcompose.github.model.repository

interface UserRepository {
    suspend fun getUser(userName: String): User
}