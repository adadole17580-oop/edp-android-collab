package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Home
                    ) {
                        composable<Home> {
                            HomeScreen(onShowGreeting = { typedName ->
                                navController.navigate(Greeting(userName = typedName))
                            })
                        }
                        composable<Greeting> { backStackEntry ->
                            val greeting = backStackEntry.toRoute<Greeting>()
                            GreetingScreen(userName = greeting.userName)
                        }
                    }
                }
            }
        }
    }
}