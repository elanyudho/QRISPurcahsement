package com.example.qrispurcahsement.pref

import android.content.Context
import android.content.SharedPreferences
import com.example.qrispurcahsement.domain.model.HistoryList
import com.example.qrispurcahsement.domain.model.User
import com.google.gson.Gson

class PrefInfo constructor(context: Context) {
    private val gson = Gson()
    private val sp : SharedPreferences by lazy {
        context.getSharedPreferences(SESSION_NAME, Context.MODE_PRIVATE)
    }

    private val spe : SharedPreferences.Editor by lazy {
        sp.edit()
    }

    fun clear() {
        spe.clear().apply()
    }

    var isFirstTime: Boolean
        get() = sp.getBoolean(IS_FIRST_TIME, true)
        set(value) = spe.putBoolean(IS_FIRST_TIME, value).apply()

    var user: User?
        get() = getUserData()
        set(value) {
            val json = gson.toJson(value)
            spe.putString(USER_PROFILE,json)
            spe.apply()
        }

    var historyList: HistoryList?
        get() = getHistoryTransactionList()
        set(value) {
            val json = gson.toJson(value)
            spe.putString(HISTORY_TRANSACTION,json)
            spe.apply()
        }

    companion object {
        private const val SESSION_NAME = "pref"
        private const val USER_PROFILE = "user_profile"
        private const val HISTORY_TRANSACTION = "history_transaction"
        private const val IS_FIRST_TIME = "first_time"
    }
    private fun getUserData(): User? {
        return try {
            gson.fromJson(
                sp.getString(USER_PROFILE,""), User::class.java)
        }catch (e: Exception){
            User()
        }
    }

    private fun getHistoryTransactionList(): HistoryList? {
        return try {
            gson.fromJson(
                sp.getString(HISTORY_TRANSACTION, ""), HistoryList::class.java
            )
        } catch (e: Exception) {
            HistoryList()
        }
    }
}