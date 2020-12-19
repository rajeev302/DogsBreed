package com.example.dogsbreed.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsbreed.R

//adapter used to show breed list on the screen
class BreedListAdapter(
    val dataSet: MutableList<String>,
    val callback: BreedLisAdapterCallback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return dataSet.count() // retuning the count of dataset that should be shown on the ui
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val issueHolder = holder as BreedListViewHolder
        issueHolder.breedTextView.text = dataSet[position] // binding breed name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_breed_list_row, parent, false) // returning the view that should be shown for each row
        return BreedListViewHolder(itemView)
    }

    inner class BreedListViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val breedTextView: TextView = itemView.findViewById(R.id.breed_name_text_view)

        init {
            itemView.setOnClickListener {
                callback.rowClicked(adapterPosition) // assigning click listener for breed name click
            }
        }
    }

//    interface used for click communication
    interface BreedLisAdapterCallback {
        fun rowClicked(position: Int)
    }
}