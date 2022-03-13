package ru.yundon.weatherforecast.presentation.mainactivity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.yundon.weatherforecast.domain.usecases.AddCitiesWeatherUseCase
import ru.yundon.weatherforecast.domain.usecases.DataRequestCitiesWeatherUseCase
import ru.yundon.weatherforecast.domain.usecases.GetCitiesWeatherListUseCase
import ru.yundon.weatherforecast.domain.usecases.GetCityWeatherByNameUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCitiesWeatherListUseCase: GetCitiesWeatherListUseCase,
    private val getCityWeatherByNameUseCase: GetCityWeatherByNameUseCase,
    private val addCitiesWeatherUseCase: AddCitiesWeatherUseCase,
    private val requestCitiesWeatherUseCase: DataRequestCitiesWeatherUseCase
): ViewModel() {

    init {
        Log.d("MyTag", "VM CREATED")
    }

    val citiesWeatherList = getCitiesWeatherListUseCase.getCitiesWeatherList()

    fun requestCitiesWeatherInfo(){

        viewModelScope.launch(Dispatchers.IO) {
            Log.d("MyTag", "ViewModel - запрос на сайт")
            requestCitiesWeatherUseCase.requestCitiesWeather()
        }
    }
}