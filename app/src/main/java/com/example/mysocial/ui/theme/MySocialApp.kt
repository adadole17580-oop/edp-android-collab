package com.example.mysocial.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun MySocialApp(
    postsVm: PostsViewModel,
    themeVm: ThemeViewModel
) {
    var tab by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = tab == 0,
                    onClick = {
                        tab = 0
                    },
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Posts")
                    }
                )

                NavigationBarItem(
                    selected = tab == 1,
                    onClick = {
                        tab = 1
                    },
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text("Profile")
                    }
                )
            }
        }
    ) { padding ->

        Box(
            Modifier.padding(padding)
        ) {

            if (tab == 0) {
                PostsScreen(postsVm)
            } else {
                ProfileScreen(
                    postsVm,
                    themeVm
                )
            }
        }
    }
}