package com.example.dogsbreed.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.dogsbreed.R
import com.example.dogsbreed.viewmodel.ImageActivityViewModel
import kotlinx.android.synthetic.main.activity_image.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_breed_list_row.*

class ImageActivity : AppCompatActivity() {
    companion object {
        const val DOG_BREED_NAME = "dogBreedName"
    }

    private lateinit var breedImageView: ImageView
    private lateinit var breedNameTextView: TextView
    private lateinit var viewmodel: ImageActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        viewmodel = ViewModelProvider(this).get(ImageActivityViewModel::class.java)

        setupUi()
        fireApiCall()
    }

    private fun fireApiCall(){
        viewmodel.getImageData({

        }, {

        })
    }

    private fun setupUi(){
        breedImageView = breed_image_view
        breedNameTextView = breed_name_text_view
    }
}