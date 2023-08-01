package com.example.qrispurcahsement.presentation.viewmodel

import com.example.qrispurcahsement.domain.model.User
import com.example.qrispurcahsement.pref.PrefInfo
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class HomeViewModelTest {

    // Mock PrefInfo
    @Mock
    private lateinit var mockPrefInfo: PrefInfo

    // Create an instance of HomeViewModel
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        // Initialize Mockito
        MockitoAnnotations.openMocks(this)

        // Set up the behavior of the mockPrefInfo
        `when`(mockPrefInfo.user).thenReturn(User(balance = "100000"))

        // Create HomeViewModel instance with the mockPrefInfo
        homeViewModel = HomeViewModel()

        homeViewModel.setPrefInfo(mockPrefInfo)
    }

    @Test
    fun getBalanceInfo() {
        homeViewModel.getBalanceInfo()

        // Assert the expected value
        assertEquals("100000", homeViewModel.uiState.value)
    }
}