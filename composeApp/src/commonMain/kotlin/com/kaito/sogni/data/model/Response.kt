package com.kaito.sogni.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerativeImageResponse(
    val status: String,
    val items: List<ImageResponse>,
    val cost: Float
)

@Serializable
data class ImageResponse(
    val image: String,
    @SerialName("image_resource_url")
    val url: String
)