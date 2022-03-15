package ru.yundon.weatherforecast.presentation.mainactivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.yundon.weatherforecast.domain.usecases.DataRequestCitiesWeatherUseCase
import ru.yundon.weatherforecast.domain.usecases.GetCitiesWeatherListUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCitiesWeatherListUseCase: GetCitiesWeatherListUseCase,
    private val requestCitiesWeatherUseCase: DataRequestCitiesWeatherUseCase
): ViewModel() {

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    val citiesWeatherList = getCitiesWeatherListUseCase.getCitiesWeatherList()

    fun requestCitiesWeatherInfo(){

        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            _error.postValue(requestCitiesWeatherUseCase.requestCitiesWeather())
            _isLoading.postValue(false)
        }

    }
}