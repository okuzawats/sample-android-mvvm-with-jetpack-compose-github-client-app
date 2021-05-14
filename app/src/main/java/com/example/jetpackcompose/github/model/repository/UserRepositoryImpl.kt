package com.example.jetpackcompose.github.model.repository

import com.example.jetpackcompose.github.model.remote_data_source.GitHubUser
import com.example.jetpackcompose.github.model.remote_data_source.RemoteDataSource
import javax.inject.Inject

/**
 * [UserRepository]の実装クラス
 */
class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : UserRepository {
    override suspend fun getUser(userName: String): User {
        return remoteDataSource.getGitHubUser(userName = userName).toUser()
    }
}

/**
 * [GitHubUser]を[User]に変換する拡張関数
 */
private fun GitHubUser.toUser(): User {
    return User(
        userId = UserId(value = id),
        name = name,
        avatarImage = NetworkImage(url = Url(value = avatarUrl)),
        blogUrl = Url(value = blog)
    )
}