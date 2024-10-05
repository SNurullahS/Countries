package com.nurullahsevinckan.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nurullahsevinckan.countries.R
import com.nurullahsevinckan.countries.viewmodel.CountryViewModel


class CountryFragment : Fragment() {

    private lateinit var viewModel : CountryViewModel
    private lateinit var countryName : TextView
    private lateinit var countrRegion : TextView
    private lateinit var countryCapital : TextView
    private lateinit var countryCurrency : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countryName = view.findViewById<TextView>(R.id.countryName)
        countrRegion = view.findViewById<TextView>(R.id.countryRegion)
        countryCapital = view.findViewById<TextView>(R.id.countryCapital)
        countryCurrency = view.findViewById<TextView>(R.id.countryCurrency)


        val viewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        viewModel.getDataFromRoom()

    }


    private fun observeListData(){

        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {country->
            country?.let{
                countryName.text = country.countryName
                countryCapital.text = country.countryCapital
                countrRegion.text = country.countryRegion
                countryCurrency.text = country.countryCurrency
            }
        })

    }



}