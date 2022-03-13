package ru.yundon.weatherforecast.domain.usecases

import ru.yundon.weatherforecast.domain.CitiesWeatherRepository
import ru.yundon.weatherforecast.domain.model.CityWeatherItem
import javax.inject.Inject

class AddCitiesWeatherUseCase @Inject constructor(
    private val citiesWeatherRepository: CitiesWeatherRepository
    ){

    suspend fun addCityWeather(cityItem: CityWeatherItem) {
        citiesWeatherRepository.addCityWeather(cityItem)
    }
}