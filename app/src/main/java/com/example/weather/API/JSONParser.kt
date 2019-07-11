package com.example.weather.API

import com.example.weather.Models.CurrentWeather
import com.example.weather.Models.Day
import org.json.JSONObject
import com.example.weather.Extension.iterator
import com.example.weather.Models.Hour

//funciones de alto nivel

fun getCurrentWeatherFromJson(response: JSONObject):CurrentWeather{

    val currentJSON = response.getJSONObject("currently")

    with(currentJSON){

        val currentWeather = CurrentWeather(time = getLong("time"),
                                            icon = getString("icon"),
                                            summary = getString("summary"),
                                            temp = getDouble("temperature"),
                                            precip = getDouble("precipProbability"))

        return currentWeather

    }
}


fun getDailyWeatherFromJson(response: JSONObject):ArrayList<Day>{

    val dailyJSON = response.getJSONObject("daily") //crear estas constantes

    val timeZone = response.getString("timezone")

    val dayJSONArray = dailyJSON.getJSONArray("data")

    val days = ArrayList<Day>()

    for (jsonDay in dayJSONArray){

        val minTemp = jsonDay.getDouble("temperatureMin")

        val maxTemp = jsonDay.getDouble("temperatureMax")

        val time = jsonDay.getLong("time")


        days.add(Day(time, minTemp, maxTemp, timeZone))

    }

    return days

}



fun getHourlyWeatherFromJson(response: JSONObject):ArrayList<Hour>{

    val hourlyJSON = response.getJSONObject("hourly") //crear estas constantes

    val timeZone = response.getString("timezone")

    val hourlyJSONArray = hourlyJSON.getJSONArray("data")

    val hours = ArrayList<Hour>()

    for (jsonHour in hourlyJSONArray){

        val time: Long = jsonHour.getLong("time")

        val temperature = jsonHour.getDouble("temperature")

        val precipProb = jsonHour.getDouble("precipProbability")

        hours.add(Hour(time, temperature, precipProb,timeZone))

    }

    return hours

}
