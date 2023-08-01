package com.example.qrispurcahsement.presentation.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.qrispurcahsement.domain.model.History
import com.example.qrispurcahsement.presentation.viewmodel.HistoryViewModel
import com.example.qrispurcahsement.ui.component.historycomponent.HistoryItem

@Composable
fun HistoryScreen(context: Context = LocalContext.current, historyViewModel: HistoryViewModel = viewModel()) {

    val historyList by historyViewModel.historyListState.collectAsState()
    historyViewModel.getHistories()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .background(Color.White)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Riwayat Transaksi",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (historyList.isEmpty()) {
                Text(
                    modifier = Modifier.fillMaxSize(),
                    text = "Riwayat Transaksi Tidak Ada",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            } else HistoryList(histories = historyList)
        }

    }
}

@Composable
private fun HistoryList(
    modifier: Modifier = Modifier,
    histories: List<History>
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = histories) {
            HistoryItem(it)
        }
    }
}

@Composable
@Preview
fun HistoryPreview() {
    HistoryPreview()
}