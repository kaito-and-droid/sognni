package com.kaito.sogni.data.repository

import com.kaito.sogni.data.model.GenerativeImageRequest
import com.kaito.sogni.data.model.GenerativeImageResponse
import com.kaito.sogni.data.model.ImageResponse
import com.kaito.sogni.data.model.SogniExp
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

interface IImageRepo {
    suspend fun genImage(request: GenerativeImageRequest): Result<GenerativeImageResponse>
}

class ImageRepoImpl(
    private val client: HttpClient
): IImageRepo {
    override suspend fun genImage(request: GenerativeImageRequest): Result<GenerativeImageResponse> {
        return runCatching {
            delay(2_500)
            Result.success(
                GenerativeImageResponse(
                    status = "success",
                    items = listOf(
                        ImageResponse(
                            image = "",
                            url = "https://img.freepik.com/free-vector/flat-creativity-concept-illustration_52683-64279.jpg"
                        )
                    ),
                    cost = 1F
                )
            )
        }.getOrElse {
            Result.failure(SogniExp.UnknownExp(it))
        }
    }

}