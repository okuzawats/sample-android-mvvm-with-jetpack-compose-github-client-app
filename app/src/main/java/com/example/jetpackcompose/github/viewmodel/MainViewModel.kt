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

/**
 * メイン画面に対するViewModel
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    /**
     * Viewの状態を表すsealed class
     */
    sealed class UiState {

        /**
         * 初期状態
         */
        object Initial : UiState()

        /**
         * 読み込み中
         */
        object Loading : UiState()

        /**
         * 読み込み成功
         */
        data class Success(val user: User) : UiState()

        /**
         * 読み込み失敗
         */
        object Failure : UiState()
    }

    /**
     * Viewの状態を[UiState]として表すMutableState
     */
    val uiState: MutableState<UiState> = mutableStateOf(UiState.Initial)

    /**
     * 検索フォームに入力された文字列を表すMutableState
     */
    val searchQuery: MutableState<String> = mutableStateOf("")

    /**
     * 検索を実行する。
     *
     * searchQueryから検索フォームに入力された文字列を取得し、
     * Repositoryを経由してユーザを問い合わせる。
     */
    fun onSearchTapped() {
        val searchQuery: String = searchQuery.value

        viewModelScope.launch {
            uiState.value = UiState.Loading
            runCatching {
                userRepository.getUser(userName = searchQuery)
            }.onSuccess {
                uiState.value = UiState.Success(user = it)
            }.onFailure {
                uiState.value = UiState.Failure
            }
        }
    }
}