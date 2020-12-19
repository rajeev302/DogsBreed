package com.example.dogsbreed.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsbreed.R
import com.example.dogsbreed.adapter.BreedListAdapter
import com.example.dogsbreed.extensions.isInternetAvailable
import com.example.dogsbreed.viewmodel.BreedListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

//lateinit variables which is initialized later
private lateinit var viewmodel: BreedListViewModel
private lateinit var breedRecyclerView: RecyclerView
private lateinit var breedAdapter: BreedListAdapter
private lateinit var noDataAvailable: TextView
private lateinit var progressBar: ProgressBar
private lateinit var rootLayout: ConstraintLayout

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
            showNoConnectionSnackbar()
        }
    }

    //    function responsible for setting up ui references
    private fun setupUi() {
        rootLayout = breed_list_root_layout
        breedRecyclerView = dogs_breed_recycler_view
        noDataAvailable = no_data_available
        progressBar = progress_bar
        breedAdapter = BreedListAdapter(viewmodel.breedList, this)
        val layoutManager = LinearLayoutManager(this)
        breedRecyclerView.layoutManager = layoutManager
        breedRecyclerView.adapter = breedAdapter
    }

    private fun showNoConnectionSnackbar(){
        val toast = Snackbar.make(rootLayout, "${this.getString(R.string.no_internet_available)}", Snackbar.LENGTH_SHORT)
        toast.addCallback(object : Snackbar.Callback() {
            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                super.onDismissed(transientBottomBar, event)
                finish()
            }
        })
        toast.show()
    }

    //    callback when any breed name row is clicked
    override fun rowClicked(position: Int) {
        val intent = Intent(this, ImageActivity::class.java)
        intent.putExtra(ImageActivity.DOG_BREED_NAME, viewmodel.breedList[position])
        startActivity(intent)
    }
}