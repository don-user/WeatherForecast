package ru.yundon.weatherforecast.presentation.cityweatheractivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.yundon.weatherforecast.databinding.ActivityCityWeatherBinding

class CityWeatherActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCityWeatherBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}