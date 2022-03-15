package ru.yundon.weatherforecast.utils

import ru.yundon.weatherforecast.domain.model.CityWeatherItem

object SortedList {

    fun sortedCityByName (list: List<CityWeatherItem>): List<CityWeatherItem>{
        return list.sortedWith(compareBy { it.name })
    }
}