package ru.yundon.weatherforecast.presentation.mainactivity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.yundon.weatherforecast.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.requestCitiesWeatherInfo()
        viewModel.citiesWeatherList.observe(this) {
                Log.d("MyTagData", "MAIN_ACTIVITY ИНФО О ПОГОДЕ $it")
            binding.textView.text = it.toString()
            }
    }
}