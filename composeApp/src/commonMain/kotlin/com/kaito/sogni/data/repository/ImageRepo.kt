package com.kaito.sogni.data.repository

import com.kaito.sogni.data.model.GenerativeImageRequest
import com.kaito.sogni.data.model.ImageResponse
import com.kaito.sogni.data.model.SogniExp
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

interface IImageRepo {
    suspend fun genImage(request: GenerativeImageRequest): Result<ImageResponse>
}

class ImageRepoImpl(
    private val client: HttpClient
): IImageRepo {
    override suspend fun genImage(request: GenerativeImageRequest): Result<ImageResponse> {
        return runCatching {
            val result = client.post("http://localhost:3000/image/generate") {
                setBody(request)
                contentType(ContentType.Application.Json)
            }
         if (result.status.isSuccess()) {
                val json = result.body<Json>()
                Result.success(ImageResponse("", ""))
            } else {
                Result.failure(SogniExp.ApiExp(result.status.value, result.status.description))
            }
        }.getOrElse {
            Result.failure(SogniExp.UnknownExp(it))
        }
    }

}