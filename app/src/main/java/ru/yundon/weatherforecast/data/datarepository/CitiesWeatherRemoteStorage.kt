package ru.yundon.weatherforecast.data.datarepository

import ru.yundon.weatherforecast.data.api.RemoteDataSource
import ru.yundon.weatherforecast.data.api.apimodel.CitiesWeatherInfo
import ru.yundon.weatherforecast.data.database.CitiesWeatherEntity

class CitiesWeatherRemoteStorage: RemoteDataSource() {

    suspend fun getCityWeatherInfo(city: String): CitiesWeatherEntity? {
        val cityWeather = getApiCitiesWeatherResult(city)
        return if (cityWeather != null) objectTransformation(cityWeather)
        else null
    }

    private fun objectTransformation(json: CitiesWeatherInfo): CitiesWeatherEntity {

        return CitiesWeatherEntity(
            json.name,
            json.main?.temp,
            json.main?.feelsLike,
            json.main?.humidity,
            json.main?.seaLevel,
            json.main?.grndLevel,
            json.wind?.speed,
            json.wind?.deg,
            json.weather[0].description,
            json.weather[0].icon,
            )
    }
}