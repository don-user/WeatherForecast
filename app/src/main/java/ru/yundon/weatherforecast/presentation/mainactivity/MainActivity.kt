package ru.yundon.weatherforecast.presentation.mainactivity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.yundon.weatherforecast.databinding.ActivityMainBinding
import ru.yundon.weatherforecast.presentation.adapter.CityWeatherAdapter
import ru.yundon.weatherforecast.presentation.cityweatheractivity.CityWeatherActivity
import ru.yundon.weatherforecast.utils.SortedList.sortedCityByName

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapterCityWeather: CityWeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.requestCitiesWeatherInfo()
        setupRecyclerView()
        observeViewModel()
        setupClickListener()

    }

    private fun setupRecyclerView() {

        adapterCityWeather = CityWeatherAdapter()
        binding.recyclerViewCity.adapter = adapterCityWeather
    }

    private fun observeViewModel(){
        viewModel.citiesWeatherList.observe(this) { it ->
            Log.d("MyTagData", "MAIN_ACTIVITY ИНФО О ПОГОДЕ $it")
            adapterCityWeather.submitList(sortedCityByName(it))
        }
    }

    private fun setupClickListener(){
        adapterCityWeather.onCityItemClickListener = {
            val intent = CityWeatherActivity.intentCityWeatherItemByName(this, it.name)
            startActivity(intent)
        }
    }
}