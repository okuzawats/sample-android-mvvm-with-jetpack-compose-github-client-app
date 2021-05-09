package com.example.jetpackcompose.github.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.jetpackcompose.github.model.repository.User

@Composable
fun UserDetailView(user: User) {
    Column {
        Text(
            text = user.id.toString()
        )
        Text(
            text = user.name
        )
        Text(
            text = user.avatarImage.url.value
        )
        Text(
            text = user.blogUrl.value
        )
    }
}