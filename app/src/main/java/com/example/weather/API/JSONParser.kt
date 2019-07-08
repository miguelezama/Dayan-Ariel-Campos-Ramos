package com.example.weather.API

import com.example.weather.Models.CurrentWeather
import com.example.weather.Models.Day
import org.json.JSONObject
import com.example.weather.Extension.iterator

//funciones de alto nivel

fun getCurrentWeatherFromJson(response: JSONObject):CurrentWeather{

    val currentJSON = response.getJSONObject("currently")

    with(currentJSON){

        val currentWeather = CurrentWeather(icon = getString("icon"),
                                            summary = getString("summary"),
                                            temp = getDouble("temperature"),
                                            precip = getDouble("precipProbability"))

        return currentWeather

    }
}


fun getDailyWeatherFromJson(response: JSONObject):ArrayList<Day>{

    val dailyJSON = response.getJSONObject("daily") //crear estas constantes

    val dayJSONArray = dailyJSON.getJSONArray("data")

    val days = ArrayList<Day>()

    for (jsonDay in dayJSONArray){

        val minTemp = jsonDay.getDouble("temperatureMin")

        val maxTemp = jsonDay.getDouble("temperatureMax")

        val time = jsonDay.getLong("time")


        days.add(Day(time, minTemp, maxTemp))

    }

    return days

}


