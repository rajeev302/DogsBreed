package com.example.dogsbreed.apiservice

import com.example.dogsbreed.constant.Constants
import com.example.dogsbreed.manager.NetworkManager
import com.example.dogsbreed.model.breedlist.BreedListResponseModel
import com.example.dogsbreed.model.imageData.ImageDataResponseModel
import com.example.dogsbreed.service.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class ApiService {
    suspend fun getBreedList(coroutineContext: CoroutineContext): BreedListResponseModel? =
        CoroutineScope(coroutineContext).async {
            val apiService =
                NetworkManager.makeRetrofitObject(Service::class.java, Constants.BASE_URL)
            val callResult = apiService.getBreedList()
            callResult.execute().body()
        }.await()

    suspend fun getImageData(
        coroutineContext: CoroutineContext,
        breed_name: String
    ): ImageDataResponseModel? = CoroutineScope(coroutineContext).async {
        val apiService = NetworkManager.makeRetrofitObject(Service::class.java, Constants.BASE_URL)
        val callResult = apiService.getImageData(breed_name)
        callResult.execute().body()
    }.await()
}