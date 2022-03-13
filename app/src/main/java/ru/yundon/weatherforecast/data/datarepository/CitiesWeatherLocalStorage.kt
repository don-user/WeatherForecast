package ru.yundon.weatherforecast.data.datarepository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import ru.yundon.weatherforecast.data.database.CitiesWeatherDatabase
import ru.yundon.weatherforecast.data.database.CitiesWeatherEntity
import javax.inject.Inject

class CitiesWeatherLocalStorage @Inject constructor(private val database: CitiesWeatherDatabase) {

    val citiesWeatherList: Flow<List<CitiesWeatherEntity>> =
        database.citiesWeatherDao().getListCitiesWeather()

    suspend fun insertCitiesIntoDatabase(item: CitiesWeatherEntity){
        database.citiesWeatherDao().insertCityWeather(item)
        Log.d("MyTag", "LocalStorage добавление в базу данных")
    }

    suspend fun getCityWeatherInfoByName(name: String): CitiesWeatherEntity{

        return database.citiesWeatherDao().getCityWeatherByName(name)
    }
}