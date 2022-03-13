package ru.yundon.weatherforecast.domain.usecases

import android.util.Log
import androidx.lifecycle.LiveData
import ru.yundon.weatherforecast.domain.CitiesWeatherRepository
import ru.yundon.weatherforecast.domain.model.CityWeatherItem
import javax.inject.Inject

class GetCitiesWeatherListUseCase @Inject constructor(
    private val citiesWeatherRepository: CitiesWeatherRepository
    ){

    fun getCitiesWeatherList(): LiveData<List<CityWeatherItem>> {
        Log.d("MyTagData", "GetCitiesWeatherListUseCase ЗАПРОС В БАЗУ ДАННЫХ")
        return citiesWeatherRepository.getCitiesWeatherList()
    }

}