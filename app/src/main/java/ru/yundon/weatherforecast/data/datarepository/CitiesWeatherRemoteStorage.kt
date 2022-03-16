package ru.yundon.weatherforecast.data.datarepository

import ru.yundon.weatherforecast.data.CityWeatherMapper
import ru.yundon.weatherforecast.data.api.RemoteDataSource
import ru.yundon.weatherforecast.data.database.CitiesWeatherEntity
import javax.inject.Inject

class CitiesWeatherRemoteStorage @Inject constructor(private val mapper: CityWeatherMapper)
    : RemoteDataSource() {

    suspend fun getCityWeatherInfo(city: String): CitiesWeatherEntity? {
        val cityWeather = getApiCitiesWeatherResult(city)
        return if (cityWeather != null) mapper.mapJsonToDataModule(cityWeather)
        else null
    }


}