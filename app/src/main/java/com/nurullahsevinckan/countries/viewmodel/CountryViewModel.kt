package com.nurullahsevinckan.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nurullahsevinckan.countries.model.Country

class CountryViewModel: ViewModel() {
    val countryLiveData = MutableLiveData<Country>()


    fun getDataFromRoom(){
    }
}