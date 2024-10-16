package com.nurullahsevinckan.countries.view

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nurullahsevinckan.countries.R
import com.nurullahsevinckan.countries.util.downloadImageFromUrl
import com.nurullahsevinckan.countries.util.placeHolderProgressBar
import com.nurullahsevinckan.countries.viewmodel.CountryViewModel


class CountryFragment : Fragment() {

    private lateinit var viewModel : CountryViewModel
    private lateinit var countryName : TextView
    private lateinit var countryRegion : TextView
    private lateinit var countryCapital : TextView
    private lateinit var countryCurrency : TextView
    private lateinit var countryImage : ImageView
    private  var countryUuid : Long = 0L

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
        countryName = view.findViewById(R.id.countryName)
        countryRegion = view.findViewById(R.id.countryRegion)
        countryCapital = view.findViewById(R.id.countryCapital)
        countryCurrency = view.findViewById(R.id.countryCurrency)
        countryImage = view.findViewById(R.id.countryImage)

        arguments?.let {
        countryUuid = CountryFragmentArgs.fromBundle(it).countriesuuid
        }

        viewModel = ViewModelProvider(this)[CountryViewModel::class.java]
        viewModel.getDataFromRoom(countryUuid)
        observeListData()
    }


    private fun observeListData(){
        println("observer called")
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {country->
            println("countrynull")
            country?.let{
                println("let içine girdim")
                countryName.text = country.countryName
                countryCapital.text = country.countryCapital
                countryRegion.text = country.countryRegion
                countryCurrency.text = country.countryCurrency
                println("currency ${countryCurrency}")
                context?.let {
                    countryImage.downloadImageFromUrl(country.imageUrl, placeHolderProgressBar(it))
                }
            }
        })

    }



}