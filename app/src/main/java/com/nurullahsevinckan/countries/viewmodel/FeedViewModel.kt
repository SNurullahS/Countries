package com.nurullahsevinckan.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nurullahsevinckan.countries.model.Country

class FeedViewModel : ViewModel() {
    var countries = MutableLiveData<ArrayList<Country>>()
    var countryError = MutableLiveData<Boolean>()
    var countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){

        val country = Country("deneme","deneme","deneme","deneme","deneme","deneme")
        val country2 = Country("deneme","deneme","deneme","deneme","deneme","deneme")
        val country3 = Country("deneme","deneme","deneme","deneme","deneme","deneme")

        val countryList  = arrayListOf<Country>(country,country2,country3)

        countries.value = countryList
        countryError.value = false
        countryLoading.value = false

    }



}