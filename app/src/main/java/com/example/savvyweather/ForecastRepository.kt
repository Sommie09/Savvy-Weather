package com.example.savvyweather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository{

    //Hold a list of daily forecast where the changes can be updated
    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()

    //This is public for out Activity to use, when _weeklyForecast is updated, this knows to and our activity carries it
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    fun loadForecast(zipcode: String){
        val randomValues = List(7) { Random.nextFloat().rem(100)}

        val forecastItems = randomValues.map {temperature ->
            DailyForecast(temperature, "Partly Cloudy")
        }

        _weeklyForecast.setValue(forecastItems)
    }


}