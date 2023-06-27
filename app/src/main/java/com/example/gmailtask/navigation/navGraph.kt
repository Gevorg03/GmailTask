package com.example.gmailtask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gmailtask.screens.EmailScreen
import com.example.gmailtask.screens.MainScreen
import com.example.gmailtask.screens.PassScreen
import com.example.gmailtask.screens.SplashScreen

@Composable
fun SetupNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = StartScreen.Splash.route
    ) {
        composable(StartScreen.Splash.route) {
            SplashScreen(
                navController = navController,
                context = LocalContext.current

            )
        }

        composable(StartScreen.Email.route) {
            EmailScreen(
                navController = navController,
            )
        }

        composable(StartScreen.Pass.route) {
            PassScreen(
                navController = navController,
                user = "user",
                context = LocalContext.current
            )
        }

        composable(StartScreen.Main.route) {
            MainScreen()
        }
    }
}