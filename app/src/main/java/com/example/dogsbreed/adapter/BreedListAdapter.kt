package com.example.dogsbreed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogsbreed.R

class BreedListAdapter(
    val context: Context,
    val dataSet: MutableList<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return dataSet.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val issueHolder = holder as BreedListViewHolder
        issueHolder.breedTextView.text = dataSet[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_breed_list_row, parent, false)
        return BreedListViewHolder(itemView)
    }

    inner class BreedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val breedTextView: TextView = itemView.findViewById(R.id.breed_name_text_view)
    }
}