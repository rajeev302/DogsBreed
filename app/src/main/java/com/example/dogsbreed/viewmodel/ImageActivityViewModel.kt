package com.example.dogsbreed.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.dogsbreed.model.imageData.ImageDataResponseModel
import com.example.dogsbreed.repository.Respository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ImageActivityViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    var breedName: String = ""
    lateinit var imageData: ImageDataResponseModel

    private val repository = Respository()

    fun getImageData(success: () -> Unit, failure: (message: String) -> Unit) = launch {
        repository.getImageData(breedName)?.let {
            imageData = it
            success.invoke()
        } ?: run {
            failure.invoke("failed to get message")
        }
    }
}