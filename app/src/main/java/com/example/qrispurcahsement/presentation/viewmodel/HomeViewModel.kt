package com.example.qrispurcahsement.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.qrispurcahsement.pref.PrefInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel()  : ViewModel() {

    private val _uiState = MutableStateFlow(String())

    val uiState: StateFlow<String> = _uiState.asStateFlow()

    private lateinit var prefInfo: PrefInfo

    fun setPrefInfo(prefInfo: PrefInfo) {
        this.prefInfo = prefInfo
    }

    fun getBalanceInfo() {
        _uiState.value = prefInfo.user?.balance!!
    }
}