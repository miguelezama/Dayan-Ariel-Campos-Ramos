package com.example.weather.API

import com.example.weather.Models.CurrentWeather
import org.json.JSONObject

class JSONParser {

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
}
