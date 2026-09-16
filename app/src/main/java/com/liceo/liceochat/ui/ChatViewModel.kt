package com.liceo.liceochat.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.liceo.liceochat.core.AppResult
import com.liceo.liceochat.data.network.NetworkModule
import com.liceo.liceochat.data.repository.ChatRepositoryImpl
import com.liceo.liceochat.domain.ChatRepository
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ChatViewModel(
    private val repository: ChatRepository
) : ViewModel() {

    var uiState: ChatUiState by mutableStateOf(ChatUiState.Loading)
        private set

    var myName: String by mutableStateOf("")
        private set

    var draft: String by mutableStateOf("")
        private set

    init {
        load()
    }

    fun onNameChange(newName: String) {
        myName = newName
    }

    fun onDraftChange(newDraft: String) {
        draft = newDraft
    }

    fun send() {
        if (myName.isBlank() || draft.isBlank()) return

        viewModelScope.launch {
            val result = repository.sendMessage(myName, draft)
            Log.d("ChatViewModel", "Send result: $result")
            when (result) {
                is AppResult.Success -> {
                    draft = ""
                    load()
                }
                is AppResult.Failure.NoInternet -> {
                    uiState = ChatUiState.Error("No internet connection (Send failed).")
                }
                is AppResult.Failure.Timeout -> {
                    uiState = ChatUiState.Error("Server timeout (Send failed).")
                }
                is AppResult.Failure.Unknown -> {
                    uiState = ChatUiState.Error("Unknown error: ${result.message}")
                }
            }
        }
    }

    fun load() {
        uiState = ChatUiState.Loading

        viewModelScope.launch {
            val r = repository.getMessages()
            Log.d("ChatViewModel", "Load result: $r")
            uiState = when (r) {
                is AppResult.Success ->
                    if (r.data.isEmpty()) {
                        ChatUiState.Empty
                    } else {
                        ChatUiState.Ready(r.data.reversed())
                    }

                AppResult.Failure.NoInternet ->
                    ChatUiState.Error("No internet connection.")

                AppResult.Failure.Timeout ->
                    ChatUiState.Error("The server took too long.")

                is AppResult.Failure ->
                    ChatUiState.Error("Something went wrong.")
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                ChatViewModel(
                    ChatRepositoryImpl(NetworkModule.chatApi)
                )
            }
        }
    }
}