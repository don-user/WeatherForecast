package ru.yundon.weatherforecast.presentation.cityweatheractivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.yundon.weatherforecast.databinding.ActivityCityWeatherBinding
import ru.yundon.weatherforecast.utils.*
import ru.yundon.weatherforecast.utils.Constants.ERROR
import ru.yundon.weatherforecast.utils.Constants.EXTRA_NAME
import ru.yundon.weatherforecast.utils.Constants.TOOLBAR_TITLE

@AndroidEntryPoint
class CityWeatherActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCityWeatherBinding.inflate(layoutInflater) }
    private val viewModel: CityWeatherViewModel by viewModels()
    private var cityName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getNameIntent()
        getCityWeatherInfo(cityName)
        setToolbar(TOOLBAR_TITLE)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        else return super.onOptionsItemSelected(item)

        return true
    }

    private fun getNameIntent(){
        if (!intent.hasExtra(EXTRA_NAME)) throw RuntimeException("Param screen name is absent")
        cityName = intent.getStringExtra(EXTRA_NAME)

    }

    private fun getCityWeatherInfo(city: String?) = with(viewModel){

        if (city != null) getCityWeatherItemByName(city)
        else showSnackBar(binding.root, ERROR)

        cityWeatherItem.observe(this@CityWeatherActivity){
            binding.apply {
                tvCity.text = it.name
                tvDescription.text = it.description
                ImageLoader.loadImage(this.iViewIcon, it.icon)
                tvTempMain.text = it.temp.tempHelper()
                tvHumidityValue.text = it.humidity.percentHelper()
                tvTempFeelsLikeValue.text = it.feelsLike.tempHelper()
                tvWindSpeedValue.text = it.windSpeed.msHelper()
                tvWindDegValue.text = it.windDeg.degreeHelper()
                tvSeaLevelValue.text = it.seaLevel.gpaHelper()
                tvGrndLevelValue.text = it.grndLevel.gpaHelper()
            }
        }
    }

    private fun setToolbar(title: String){
        setSupportActionBar(binding.toolbarWeather)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = title

    }

    companion object{

        fun intentCityWeatherItemByName(context: Context, cityWeatherName: String): Intent{
            val intent = Intent(context, CityWeatherActivity::class.java)
            intent.putExtra(EXTRA_NAME, cityWeatherName)
            return intent
        }
    }
}