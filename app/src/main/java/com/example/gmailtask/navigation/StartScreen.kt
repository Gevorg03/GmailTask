package com.example.gmailtask.navigation

sealed class StartScreen(val route: String) {
    object Splash : StartScreen("splash_screen")
    object Email : StartScreen("email_screen")
    object Pass : StartScreen("pass_screen")
    object Main : StartScreen("main_screen")
}