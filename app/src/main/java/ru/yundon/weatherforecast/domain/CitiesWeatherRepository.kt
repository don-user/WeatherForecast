package ru.yundon.weatherforecast.domain

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import ru.yundon.weatherforecast.domain.model.CityWeatherItem

interface CitiesWeatherRepository {

    fun getCitiesWeatherList(): LiveData<List<CityWeatherItem>>

    suspend fun getCitiesWeatherItem(name: String): CityWeatherItem

//    suspend fun addCityWeather(cityItem: CityWeatherItem)

    suspend fun requestCitiesWeather(city: String): Boolean
}