package com.kaito.sogni.data.model

sealed class SogniExp: Exception() {
    data class ApiExp(val code: Int, val msg: String): SogniExp()
    data class UnknownExp(val throwable: Throwable): SogniExp()
}