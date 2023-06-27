package com.example.gmailtask.bottomBarNavigation

import com.example.gmailtask.R

sealed class BottomBarScreen(
    val route: String,
    val icon: Int
) {
    object Home : BottomBarScreen(
        route = "Home",
        icon = R.drawable.ic_email
    )

    object Meet : BottomBarScreen(
        route = "Meet",
        icon = R.drawable.ic_meet
    )
}