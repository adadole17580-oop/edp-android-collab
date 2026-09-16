package com.liceo.liceochat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.liceo.liceochat.ui.ChatScreen
import com.liceo.liceochat.ui.ChatViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: ChatViewModel = viewModel(factory = ChatViewModel.Factory)

            ChatScreen(
                viewModel = viewModel
            )
        }
    }
}