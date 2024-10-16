package com.nurullahsevinckan.countries.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nurullahsevinckan.countries.model.Country


@Dao
interface CountryDao {

    // Data access object

    @Insert
    suspend fun insertAll(vararg countries: Country): List<Long>
    //Insert -> INSERT INTO
    //Suspend -> coroutine Pause & continue
    //Vararg -> Multiple country object
    // List<lONG> -> Primary keys

    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM Country WHERE uuid = :countryID")
    suspend fun getCountry(countryID : Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}