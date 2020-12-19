package com.example.dogsbreed.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.dogsbreed.repository.Respository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BreedListViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    val breedList = ArrayList<String>()
    private val repository = Respository()
    fun getBreedList(success: () -> Unit, failure: (message: String) -> Unit) = launch {
        repository.getBreedList()?.let {
            breedList.clear()
            it.message?.keys?.forEach {
                breedList.add(it)
            }
            success.invoke()
        } ?: run {
            failure.invoke("failed to get message")
        }
    }

}