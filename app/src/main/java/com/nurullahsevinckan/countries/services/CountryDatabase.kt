package com.nurullahsevinckan.countries.services

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nurullahsevinckan.countries.model.Country


@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDatabase : RoomDatabase() {
    abstract fun countryDao() : CountryDao

    //Singleton

    companion object{
        @Volatile private var instence : CountryDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instence ?: synchronized(lock){
            instence ?: makeDatabase(context).also{ countryDatabase ->
                instence = countryDatabase
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,CountryDatabase::class.java,"countrydatabase"
        ).build()
    }


}