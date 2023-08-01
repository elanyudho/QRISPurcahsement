package com.example.qrispurcahsement.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.qrispurcahsement.domain.model.History
import com.example.qrispurcahsement.pref.PrefInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HistoryViewModel(): ViewModel() {

    private val _historyListState = MutableStateFlow(emptyList<History>())

    val historyListState: StateFlow<List<History>> = _historyListState.asStateFlow()

    private lateinit var prefInfo: PrefInfo

    fun setPrefInfo(prefInfo: PrefInfo) {
        this.prefInfo = prefInfo
    }

    fun getHistories() {
        _historyListState.value = prefInfo.historyList?.historyList ?: emptyList()
    }
}