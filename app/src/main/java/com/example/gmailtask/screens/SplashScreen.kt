package com.example.gmailtask.screens

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.gmailtask.R
import com.example.gmailtask.navigation.StartScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    context: Context
) {
    val scale = remember { Animatable(0f) }
    val sh: SharedPreferences = context
        .getSharedPreferences("UserInfo", MODE_PRIVATE)
    val email = sh.getString("email", "")
    val pass = sh.getString("pass", "")

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(2000L)
        if (email?.isNotEmpty() == true && pass?.isNotEmpty() == true)
            navController.navigate(StartScreen.Main.route)
        else
            navController.navigate(StartScreen.Email.route)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.gmail_icon),
            contentDescription = "Gmail Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}