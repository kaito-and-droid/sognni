package com.kaito.sogni.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GenerativeImageRequest(
    val providers: String,
    val text: String,
    val resolution: String
)