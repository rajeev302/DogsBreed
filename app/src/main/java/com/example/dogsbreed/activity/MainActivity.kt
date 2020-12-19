package com.example.dogsbreed.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.dogsbreed.R
import com.example.dogsbreed.model.breedlist.BreedListResponseModel
import com.example.dogsbreed.viewmodel.BreedListViewModel

private lateinit var viewmodel: BreedListViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProvider(this).get(BreedListViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewmodel.getBreedList(success = {
                                         Log.d("test_dogs_breed_data", "data is ${viewmodel.breedList}")
            Toast.makeText(this, "test_dogs_breed_data ${viewmodel.breedList}", Toast.LENGTH_LONG).show()
        }, failure = {})
    }
}