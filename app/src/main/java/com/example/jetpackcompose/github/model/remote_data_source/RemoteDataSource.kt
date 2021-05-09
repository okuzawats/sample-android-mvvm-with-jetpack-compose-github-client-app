package com.example.jetpackcompose.github.model.remote_data_source

interface RemoteDataSource {
    suspend fun getGitHubUser(userName: String): GitHubUser
}