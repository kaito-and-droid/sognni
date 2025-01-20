package com.kaito.sogni.data.model

sealed interface DataState<out T> {
    data object Init: DataState<Nothing>
    data object Loading: DataState<Nothing>
    data class Success<T>(val data: T): DataState<T>
    data class Failure(val err: SogniExp): DataState<Nothing>
}