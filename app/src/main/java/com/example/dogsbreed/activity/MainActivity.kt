package com.example.dogsbreed.activity

import android.content.Intent
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
import com.example.dogsbreed.extensions.isInternetAvailable
import com.example.dogsbreed.model.breedlist.BreedListResponseModel
import com.example.dogsbreed.viewmodel.BreedListViewModel
import kotlinx.android.synthetic.main.activity_main.*

//lateinit variables which is initialized later
private lateinit var viewmodel: BreedListViewModel
private lateinit var breedRecyclerView: RecyclerView
private lateinit var breedAdapter: BreedListAdapter
private lateinit var noDataAvailable: TextView
private lateinit var progressBar: ProgressBar

class MainActivity : AppCompatActivity(), BreedListAdapter.BreedLisAdapterCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // setting layout to be shown on the screen
        viewmodel = ViewModelProvider(this).get(BreedListViewModel::class.java) // getting viewmodel from viewmodel provider
        setupUi()
        fireApiCall()
    }

    //    function responsible for calling api
    private fun fireApiCall() {
        if (this.isInternetAvailable()) {
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
        } else {
            Toast.makeText(this, "${this.getString(R.string.no_internet_available)}", Toast.LENGTH_LONG).show()
        }
    }

    //    function responsible for setting up ui references
    private fun setupUi() {
        breedRecyclerView = dogs_breed_recycler_view
        noDataAvailable = no_data_available
        progressBar = progress_bar
        breedAdapter = BreedListAdapter(viewmodel.breedList, this)
        val layoutManager = LinearLayoutManager(this)
        breedRecyclerView.layoutManager = layoutManager
        breedRecyclerView.adapter = breedAdapter
    }

    //    callback when any breed name row is clicked
    override fun rowClicked(position: Int) {
        val intent = Intent(this, ImageActivity::class.java)
        intent.putExtra(ImageActivity.DOG_BREED_NAME, viewmodel.breedList[position])
        startActivity(intent)
    }
}