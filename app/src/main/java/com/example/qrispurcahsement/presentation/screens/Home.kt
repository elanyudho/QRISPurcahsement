package com.example.qrispurcahsement.presentation.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.qrispurcahsement.domain.model.User
import com.example.qrispurcahsement.pref.PrefInfo
import com.example.qrispurcahsement.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    context: Context = LocalContext.current,
    homeViewModel: HomeViewModel = viewModel()
) {
    val pref = PrefInfo(context)
    homeViewModel.setPrefInfo(prefInfo = pref)
    if (pref.isFirstTime) {
        pref.user = User("Elan", "1000000")
        pref.isFirstTime = false
    }

    homeViewModel.getBalanceInfo()
    val balance by homeViewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .padding(16.dp)
                .background(
                    color = Color.Gray,
                    shape = RoundedCornerShape(16.dp) // Adjust the corner radius as needed
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Saldo Anda",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = balance,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}