package com.example.jetpackcompose.github.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.jetpackcompose.github.model.repository.User
import com.example.jetpackcompose.github.viewmodel.MainViewModel

@Composable
fun MainView(mainViewModel: MainViewModel) {
    val uiState: MainViewModel.UiState by mainViewModel.uiState

    Column(Modifier.fillMaxWidth()) {
        SearchView(
            searchQuery = mainViewModel.searchQuery,
            onSearchButtonTapped = mainViewModel::onSearchTapped,
        )
        when (uiState) {
            is MainViewModel.UiState.None -> {
                EmptyView()
            }
            is MainViewModel.UiState.Loading -> {
                LoadingView()
            }
            is MainViewModel.UiState.Loaded -> {
                UserDetailView(user = uiState.requireUser())
            }
            is MainViewModel.UiState.Error -> {
                ErrorView()
            }
        }
    }
}

fun MainViewModel.UiState.requireUser(): User {
    if (this !is MainViewModel.UiState.Loaded) throw IllegalStateException("user is not loaded")
    return user
}