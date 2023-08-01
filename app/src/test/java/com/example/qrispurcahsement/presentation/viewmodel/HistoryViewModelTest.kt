package com.example.qrispurcahsement.presentation.viewmodel

import com.example.qrispurcahsement.domain.model.History
import com.example.qrispurcahsement.domain.model.HistoryList
import com.example.qrispurcahsement.domain.model.User
import com.example.qrispurcahsement.pref.PrefInfo
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HistoryViewModelTest {

    // Mock PrefInfo
    @Mock
    private lateinit var mockPrefInfo: PrefInfo

    // Create an instance of PaymentViewModel
    private lateinit var historyViewModel: HistoryViewModel

    @Before
    fun setup() {
        // Initialize Mockito
        MockitoAnnotations.openMocks(this)

        // Set up the behavior of the mockPrefInfo
        Mockito.`when`(mockPrefInfo.historyList).thenReturn(
            HistoryList(
                historyList = listOf(
                    History("MERCHANT MOCK TEST", "50000")
                )
            )
        )

        // Create HistoryViewModel instance with the mockPrefInfo
        historyViewModel = HistoryViewModel()

        historyViewModel.setPrefInfo(mockPrefInfo)
    }

    @Test
    fun getBalanceInfo() {
        historyViewModel.getHistories()

        // Assert the expected value
        assertEquals(
            listOf(
                History("MERCHANT MOCK TEST", "50000")
            ), historyViewModel.historyListState.value
        )
    }
}