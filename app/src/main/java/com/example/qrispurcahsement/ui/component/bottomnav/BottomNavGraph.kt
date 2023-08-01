package com.example.qrispurcahsement.ui.component.bottomnav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.qrispurcahsement.pref.PrefInfo
import com.example.qrispurcahsement.presentation.screens.HistoryScreen
import com.example.qrispurcahsement.presentation.screens.HomeScreen
import com.example.qrispurcahsement.presentation.screens.PaymentScreen
import com.example.qrispurcahsement.presentation.viewmodel.PaymentViewModel

@Composable
fun BottomNavGraph(context: Context = LocalContext.current, navController: NavHostController, paymentViewModel: PaymentViewModel = viewModel()
) {
    val prefInfo = PrefInfo(context = context)
    paymentViewModel.setPrefInfo(prefInfo = prefInfo)

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Payment.route) {
            PaymentScreen(context, paymentViewModel)
        }
        composable(route = BottomBarScreen.History.route) {
            HistoryScreen()
        }
    }
}