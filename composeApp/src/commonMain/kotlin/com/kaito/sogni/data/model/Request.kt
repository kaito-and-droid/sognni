package com.kaito.sogni.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GenerativeImageRequest(
    val prompt: String,
    val style: String
)