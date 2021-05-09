package com.example.jetpackcompose.github.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompose.github.model.repository.User
import com.example.jetpackcompose.github.model.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    sealed class UiState {
        object None : UiState()
        object Loading : UiState()
        data class Loaded(val user: User) : UiState()
        object Error : UiState()
    }

    val uiState: MutableState<UiState> = mutableStateOf(UiState.None)

    val searchQuery: MutableState<String> = mutableStateOf("")

    fun onSearchTapped() {
        val searchQuery: String = searchQuery.value

        viewModelScope.launch {
            uiState.value = UiState.Loading
            runCatching {
                userRepository.getUser(userName = searchQuery)
            }.onSuccess {
                uiState.value = UiState.Loaded(user = it)
            }.onFailure {
                uiState.value = UiState.Error
            }
        }
    }
}