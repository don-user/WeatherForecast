package ru.yundon.weatherforecast.utils

import ru.yundon.weatherforecast.utils.Constants.ICON_TEMP
import kotlin.math.roundToInt



fun Double?.tempHelper() : String{

    return "${this?.roundToInt()}$ICON_TEMP"
}


