package ru.yundon.weatherforecast.domain.usecases

import ru.yundon.weatherforecast.domain.CitiesWeatherRepository
import ru.yundon.weatherforecast.utils.Cities
import javax.inject.Inject

class DataRequestCitiesWeatherUseCase @Inject constructor(
    private val citiesWeatherRepository: CitiesWeatherRepository
    ){

    suspend fun requestCitiesWeather(){
        for (item in Cities.values()) {
            citiesWeatherRepository.requestCitiesWeather(item.name)
        }
    }
}