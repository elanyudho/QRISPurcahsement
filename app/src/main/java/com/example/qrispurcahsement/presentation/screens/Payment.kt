package com.example.qrispurcahsement.presentation.screens

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import com.example.qrispurcahsement.pref.PrefInfo
import com.example.qrispurcahsement.presentation.dialog.SuccessDialog
import com.example.qrispurcahsement.presentation.viewmodel.PaymentViewModel
import com.example.qrispurcahsement.ui.component.scanner.PreviewScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PaymentScreen(
    context: Context,
    paymentViewModel: PaymentViewModel
) {
    val isOnPaymentProcess by paymentViewModel.isOnPaymentProcessState.collectAsState()
    val isPaymentSuccess by paymentViewModel.isPaySuccessState.collectAsState()
    val newUserInfo by paymentViewModel.newUserInfoState.collectAsState()
    val newHistoryList by paymentViewModel.newHistoryListState.collectAsState()

    val pref = PrefInfo(context = context)
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    if (isPaymentSuccess) {
        pref.user = newUserInfo
        pref.historyList = newHistoryList

        SuccessDialog(
            onDismiss = {
                 paymentViewModel.setPaymentProcess(false)
                 paymentViewModel.setPaymentSuccess(false)
            }
        )
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (permissionState.hasPermission) {
                // Show DetailTransaction or PreviewScreen based on isOnPaymentProcess
                if (isOnPaymentProcess) {
                    DetailTransaction(paymentViewModel = paymentViewModel)
                } else {
                    PreviewScreen(paymentViewModel)
                }
            } else {
                // Show a button to request camera permission
                Button(
                    onClick = {
                        permissionState.launchPermissionRequest()
                    }
                ) {
                    Text("Request Camera Permission")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Scan QR Code",
                modifier = Modifier.padding(top = 32.dp),
                color = Color.White
            )

        }
    }
}

@Composable
fun DetailTransaction(context: Context = LocalContext.current, paymentViewModel: PaymentViewModel) {
    val qrInfo by paymentViewModel.qrInfoState.collectAsState()
    val userInfo = PrefInfo(context = context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Detail Transaksi",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
        Text(
            text = "Bank: ${qrInfo.bank}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp),
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )
        Text(
            text = "Nomor Transaksi: ${qrInfo.idTransaction}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp),
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            textAlign = TextAlign.Start

        )
        Text(
            text = "Merchant: ${qrInfo.merchant}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp),
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            textAlign = TextAlign.Start

        )
        Text(
            text = "Nominal: ${qrInfo.nominal}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp),
            color = Color.Black,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {

            Button(
                onClick = {
                    if (userInfo.user?.balance?.toInt()!! >= qrInfo.nominal.toInt()) {
                        paymentViewModel.pay(qrInfo.merchant, qrInfo.nominal)
                    } else {
                        Toast.makeText(context, "Saldo Anda Tidak Cukup", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp)
                    .height(48.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)

            ) {
                Text(
                    text = "Bayar",
                    color = Color.White,
                    fontSize = 14.sp,
                )
            }

            Button(
                onClick = {
                    paymentViewModel.setPaymentProcess(false)
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .height(48.dp)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)

            ) {
                Text(
                    text = "Batal",
                    color = Color.White,
                    fontSize = 14.sp,
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PaymentPreview() {
}