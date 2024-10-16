package com.nurullahsevinckan.countries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.nurullahsevinckan.countries.model.Country
import com.nurullahsevinckan.countries.services.CountryAPIService
import com.nurullahsevinckan.countries.services.CountryDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application){
    private val countryAPIService = CountryAPIService()
    private val disposable = CompositeDisposable()


    var countries = MutableLiveData<List<Country>>()
    var countryError = MutableLiveData<Boolean>()
    var countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
    countryLoading.value = true
        disposable.add(
            countryAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        storeDataInSQLite(t)
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }

                })
        )

    }

    private fun showCountries(countryList : List<Country>) {
        countries.value = countryList
        countryLoading.value = false
        countryError.value =false
    }

    private fun storeDataInSQLite(list : List<Country>){
        //we use launch -> Coroutine new thread to store data asynchronously
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*list.toTypedArray()) // list -> individual
            var i  = 0
            while(i < list.size){
                list[i].uuid = listLong[i].toInt()
                i += 1
            }

            showCountries(list)
        }
    }


}