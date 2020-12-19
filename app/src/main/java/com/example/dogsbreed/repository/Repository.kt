package com.example.dogsbreed.repository

import android.content.Context
import com.example.dogsbreed.apiservice.ApiService
import com.example.dogsbreed.extensions.isInternetAvailable
import com.example.dogsbreed.model.breedlist.BreedListResponseModel
import com.example.dogsbreed.model.imageData.ImageDataResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext

class Respository: CoroutineScope {
    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    suspend fun getBreedList(): List<BreedListResponseModel>? {
        return CoroutineScope(coroutineContext).async {
            return@async ApiService().getBreedList(coroutineContext)
        }.await()
    }

    suspend fun getImageData(context: Context, breed_name: String): ImageDataResponseModel? {
        return CoroutineScope(coroutineContext).async {
            return@async ApiService().getImageData(coroutineContext, breed_name)
        }.await()
    }
}