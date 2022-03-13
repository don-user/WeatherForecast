package ru.yundon.weatherforecast.data.datarepository

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import ru.yundon.weatherforecast.data.CityWeatherMapper
import ru.yundon.weatherforecast.data.database.CitiesWeatherEntity
import ru.yundon.weatherforecast.domain.CitiesWeatherRepository
import ru.yundon.weatherforecast.domain.model.CityWeatherItem
import javax.inject.Inject

class CitiesWeatherRepositoryImpl @Inject constructor(
    private val localStorage: CitiesWeatherLocalStorage,
    private val removeStorage: CitiesWeatherRemoteStorage,
    private val mapper: CityWeatherMapper
) : CitiesWeatherRepository {


    override fun getCitiesWeatherList(): LiveData<List<CityWeatherItem>> = Transformations.map(

        localStorage.citiesWeatherList.asLiveData()
    ){
        mapper.mapListToDomain(it)
    }

    override suspend fun getCitiesWeatherItem(name: String): CityWeatherItem {

        val cityWeather = localStorage.getCityWeatherInfoByName(name)
        return mapper.mapItemToDomain(cityWeather)
    }

    override suspend fun addCityWeather(cityItem: CityWeatherItem) {

        localStorage.insertCitiesIntoDatabase(mapper.mapItemToData(cityItem))
    }

    override suspend fun requestCitiesWeather(city: String) {

        val cityWeatherInfo = removeStorage.getCityWeatherInfo(city)
//        Log.d("MyTag", "CitiesWeatherRepositoryImpl, запрос погоды город $city ответ $cityWeatherInfo")
        if (cityWeatherInfo != null) localStorage.insertCitiesIntoDatabase(cityWeatherInfo)

    }


}