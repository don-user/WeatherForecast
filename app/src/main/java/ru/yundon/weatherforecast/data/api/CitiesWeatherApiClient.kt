package ru.yundon.weatherforecast.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.yundon.weatherforecast.utils.Constants.BASE_URL

object CitiesWeatherApiClient {

    val apiCitiesWeather: ApiCitiesWeather by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return@lazy retrofit.create(ApiCitiesWeather::class.java)
    }
}