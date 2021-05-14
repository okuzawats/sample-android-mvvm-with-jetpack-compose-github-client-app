package com.example.jetpackcompose.github.model.remote_data_source

import javax.inject.Inject

/**
 * [RemoteDataSource]の実装クラス
 */
class RemoteDataSourceImpl @Inject constructor(
    private val apiClient: ApiClient,
) : RemoteDataSource {
    override suspend fun getGitHubUser(userName: String): GitHubUser {
        val response = apiClient.getGitHubUser(userName = userName)
        if (response.isSuccessful) {
            val gitHubUser: GitHubUser = requireNotNull(response.body())
            return gitHubUser
        }
        throw HttpException()
    }
}

/**
 * ResponseがisSuccessful != trueだった時に投げられる例外
 */
class HttpException : Throwable()