package com.example.qrispurcahsement.presentation.viewmodel

import android.util.Log
import com.example.qrispurcahsement.domain.model.History
import com.example.qrispurcahsement.domain.model.HistoryList
import com.example.qrispurcahsement.domain.model.QRInfo
import com.example.qrispurcahsement.domain.model.User
import com.example.qrispurcahsement.pref.PrefInfo
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PaymentViewModelTest {

    // Mock PrefInfo
    @Mock
    private lateinit var mockPrefInfo: PrefInfo

    // Create an instance of PaymentViewModel
    private lateinit var paymentViewModel: PaymentViewModel

    @Before
    fun setup() {
        // Initialize Mockito
        MockitoAnnotations.openMocks(this)

        Mockito.`when`(mockPrefInfo.user).thenReturn(User(name = "Elan", balance = "100000"))

        // Create PaymentViewModel instance with the mockPrefInfo
        paymentViewModel = PaymentViewModel()

        paymentViewModel.setPrefInfo(mockPrefInfo)

        paymentViewModel.setQrInfo("BNI.ID12345678.MERCHANT MOCK TEST.50000")

    }

    @Test
    fun setQrInfo() {
        paymentViewModel.setQrInfo("BNI.ID12345678.MERCHANT MOCK TEST.50000")

        Assert.assertEquals(QRInfo("BNI","ID12345678","50000","MERCHANT MOCK TEST"), paymentViewModel.qrInfoState.value)
    }

    @Test
    fun pay() {
        paymentViewModel.pay(paymentViewModel.qrInfoState.value.merchant, paymentViewModel.qrInfoState.value.nominal)

        // Assert the expected value
        //check success update user info
        Assert.assertEquals(User("Elan", "50000"), paymentViewModel.newUserInfoState.value)
        //check payment success
        Assert.assertEquals(true, paymentViewModel.isPaySuccessState.value)
        //check success history added
        Assert.assertEquals(HistoryList(historyList = listOf(History("MERCHANT MOCK TEST", "50000"))), paymentViewModel.newHistoryListState.value)

    }

}