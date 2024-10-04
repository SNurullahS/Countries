package com.nurullahsevinckan.countries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nurullahsevinckan.countries.R
import com.nurullahsevinckan.countries.model.Country

class CountryAdapter(val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country,parent,false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.countryText.text = countryList[position].countryName
        holder.regionText.text = countryList[position].countryRegion

    }

    //This is for swiperefreshlayout in fragment feed xml
    fun updateCountryList(newCountryList : ArrayList<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}