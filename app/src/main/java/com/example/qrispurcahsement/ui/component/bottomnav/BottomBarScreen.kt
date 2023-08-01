package com.example.qrispurcahsement.ui.component.bottomnav

import com.example.qrispurcahsement.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_home
    )

    object Payment : BottomBarScreen(
        route = "payment",
        title = "Payment",
        icon = R.drawable.ic_qr
    )

    object History : BottomBarScreen(
        route = "history",
        title = "History",
        icon = R.drawable.ic_history
    )
}