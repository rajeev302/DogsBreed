package com.example.dogsbreed.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsbreed.R
import com.example.dogsbreed.adapter.BreedListAdapter
import com.example.dogsbreed.model.breedlist.BreedListResponseModel
import com.example.dogsbreed.viewmodel.BreedListViewModel
import kotlinx.android.synthetic.main.activity_main.*

private lateinit var viewmodel: BreedListViewModel
private lateinit var breedRecyclerView: RecyclerView
private lateinit var breedAdapter: BreedListAdapter
private lateinit var noDataAvailable: TextView
private lateinit var progressBar: ProgressBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel = ViewModelProvider(this).get(BreedListViewModel::class.java)

        setupUi()
        fireApiCall()
    }

    private fun fireApiCall(){
        viewmodel.getBreedList({
            breedAdapter.notifyDataSetChanged()
            progressBar.visibility = View.GONE
            noDataAvailable.visibility = View.GONE
            breedRecyclerView.visibility = View.VISIBLE
        }, {
            progressBar.visibility = View.GONE
            noDataAvailable.visibility = View.VISIBLE
            breedRecyclerView.visibility = View.GONE
        })
    }

    private fun setupUi(){
        breedRecyclerView = dogs_breed_recycler_view
        noDataAvailable = no_data_available
        progressBar = progress_bar
        breedAdapter = BreedListAdapter(this, viewmodel.breedList)
        val layoutManager = LinearLayoutManager(this)
        breedRecyclerView.layoutManager = layoutManager
        breedRecyclerView.adapter = breedAdapter
    }
}