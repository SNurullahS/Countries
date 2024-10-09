package com.nurullahsevinckan.countries.services

import com.nurullahsevinckan.countries.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {
    // API Link
    // https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    //Single is just one time get data , we use it instead of using Observable method
    fun getCountries():Single<List<Country>>

}