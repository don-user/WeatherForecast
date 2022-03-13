package ru.yundon.weatherforecast.data.api

import retrofit2.Response
import ru.yundon.weatherforecast.data.api.apimodel.CitiesWeatherInfo

abstract class RemoteDataSource {

    suspend fun getApiCitiesWeatherResult(city: String): CitiesWeatherInfo?{
        val response: Response<CitiesWeatherInfo>
        return try {
            response = CitiesWeatherApiClient.apiCitiesWeather.getCitiesWeatherInfo(city)
//            Log.d("MyTag", "RemoteDatabase запрос в сеть ответ ${response.body()} $city")
            response.body()

        } catch (e: Exception) {
            null
        }
    }


}