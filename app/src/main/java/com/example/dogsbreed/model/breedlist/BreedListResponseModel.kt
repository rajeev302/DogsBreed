package com.example.dogsbreed.model.breedlist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BreedListResponseModel(
    @Json(name = "message")
    var message: Message?,
    @Json(name = "status")
    var status: String?
)