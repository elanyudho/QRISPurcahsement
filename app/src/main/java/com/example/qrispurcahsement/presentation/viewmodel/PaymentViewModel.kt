package com.example.qrispurcahsement.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.qrispurcahsement.domain.model.History
import com.example.qrispurcahsement.domain.model.HistoryList
import com.example.qrispurcahsement.domain.model.QRInfo
import com.example.qrispurcahsement.domain.model.User
import com.example.qrispurcahsement.pref.PrefInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PaymentViewModel()  : ViewModel() {

    private val _isOnPaymentProcessState = MutableStateFlow(false)
    private val _qrInfoState = MutableStateFlow(QRInfo())
    private val _isPaySuccessState = MutableStateFlow(false)
    private val _newUserInfoState = MutableStateFlow(User())
    private val _newHistoryListState = MutableStateFlow(HistoryList())


    val isOnPaymentProcessState: StateFlow<Boolean> = _isOnPaymentProcessState.asStateFlow()
    val qrInfoState: StateFlow<QRInfo> = _qrInfoState.asStateFlow()
    val isPaySuccessState: StateFlow<Boolean> = _isPaySuccessState.asStateFlow()
    val newUserInfoState: StateFlow<User> = _newUserInfoState.asStateFlow()
    val newHistoryListState: StateFlow<HistoryList> = _newHistoryListState.asStateFlow()

    private lateinit var prefInfo: PrefInfo

    fun setPrefInfo(prefInfo: PrefInfo) {
        this.prefInfo = prefInfo
    }

    fun setPaymentProcess(isPayment: Boolean) {
        _isOnPaymentProcessState.value = isPayment
    }

    fun setQrInfo(rawQrInfo: String) {
        val parts = rawQrInfo.split(".")
        _qrInfoState.value = QRInfo(bank = parts[0], idTransaction = parts[1], merchant = parts[2], nominal = parts[3])
    }

    fun pay(merchant: String, nominal: String) {
        val newBalance = prefInfo.user?.balance?.toInt()?.minus(nominal.toInt())

        //update information user
        _newUserInfoState.value = User(prefInfo.user?.name!!, newBalance.toString())

        //update history
        val newHistoryList = ArrayList<History>()
        for (i in prefInfo.historyList?.historyList?: emptyList()) {
            newHistoryList.add(i)
        }
        newHistoryList.add(History(merchant, nominal))
        _newHistoryListState.value = HistoryList(newHistoryList)

        //
        _isPaySuccessState.value = true

    }

    fun setPaymentSuccess(isPayment: Boolean) {
        _isPaySuccessState.value = isPayment
    }

}