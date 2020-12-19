package com.example.dogsbreed.activity

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.dogsbreed.R
import com.example.dogsbreed.extensions.isInternetAvailable
import com.example.dogsbreed.viewmodel.ImageActivityViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {
    companion object {
        const val DOG_BREED_NAME = "dogBreedName" // constant object used for data passing between intents
    }

    //lateinit variables which is initialized later
    private lateinit var breedImageView: ImageView
    private lateinit var breedNameTextView: TextView
    private lateinit var viewmodel: ImageActivityViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var rootLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        viewmodel = ViewModelProvider(this).get(ImageActivityViewModel::class.java) // getting viewmodel from viewmodel provider
        viewmodel.breedName = intent.getStringExtra(DOG_BREED_NAME) // getting breed name from intent string extra
        setupUi()
        fireApiCall()
    }

    //    function to get data for particular breed name and then fetch image using glide
    private fun fireApiCall() {
        if (this.isInternetAvailable()) { // check if internet is available
            viewmodel.getImageData({
                Glide.with(this).load(viewmodel.imageData.message)
                        .listener(object : RequestListener<Drawable> { // glide listener to know the image loading failure or success
                            override fun onLoadFailed(
                                    e: GlideException?,
                                    model: Any?,
                                    target: Target<Drawable>?,
                                    isFirstResource: Boolean
                            ): Boolean {
                                Toast.makeText(
                                        this@ImageActivity,
                                        "failed to laod the image",
                                        Toast.LENGTH_LONG
                                ).show()
                                return false
                            }

                            override fun onResourceReady(
                                    resource: Drawable?,
                                    model: Any?,
                                    target: Target<Drawable>?,
                                    dataSource: DataSource?,
                                    isFirstResource: Boolean
                            ): Boolean {
                                breedNameTextView.text = viewmodel.breedName
                                progressBar.visibility = View.GONE
                                return false
                            }
                        }).into(breedImageView)
            }, {
                Toast.makeText(this, "failed to get the data from api", Toast.LENGTH_LONG).show()
            })
        } else {
            showNoConnectionSnackbar()
        }
    }

    private fun showNoConnectionSnackbar(){
        val toast = Snackbar.make(rootLayout, "${this.getString(R.string.no_internet_available)}", 500)
        toast.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                finish()
            }
        })
        toast.show()
    }

    //    function for setting up UI references.
    private fun setupUi() {
        rootLayout = root_layout
        breedImageView = breed_image_view
        breedNameTextView = breed_name
        progressBar = image_progress
    }
}
