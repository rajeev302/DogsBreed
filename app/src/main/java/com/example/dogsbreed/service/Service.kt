package com.example.dogsbreed.service

import com.example.dogsbreed.model.breedlist.BreedListResponseModel
import com.example.dogsbreed.model.imageData.ImageDataResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("issues")
    fun getBreedList(): Call<List<BreedListResponseModel>>

    @GET("breed/{breed_name}/images/random")
    fun getImageData(@Path("breed_name")breed_name: String): Call<ImageDataResponseModel>
}