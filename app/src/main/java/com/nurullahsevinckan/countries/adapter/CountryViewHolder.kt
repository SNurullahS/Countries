package com.nurullahsevinckan.countries.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nurullahsevinckan.countries.R

class CountryViewHolder(var view : View) : RecyclerView.ViewHolder(view) {
    val imageView: ImageView = view.findViewById(R.id.imageView)
    val countryText: TextView = view.findViewById(R.id.countryText)
    val regionText: TextView = view.findViewById(R.id.regionText)
}