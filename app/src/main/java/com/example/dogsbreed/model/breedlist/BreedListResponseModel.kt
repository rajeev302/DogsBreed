package com.example.dogsbreed.model.breedlist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//model that will be received from breed list api
@JsonClass(generateAdapter = true)
data class BreedListResponseModel(
    @Json(name = "message")
    var message: Map<String, List<String>>?,
    @Json(name = "status")
    var status: String?
)