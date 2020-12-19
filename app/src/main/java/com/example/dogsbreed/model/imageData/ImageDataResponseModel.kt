package com.example.dogsbreed.model.imageData


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageDataResponseModel(
    @Json(name = "message")
    var message: String?,
    @Json(name = "status")
    var status: String?
)