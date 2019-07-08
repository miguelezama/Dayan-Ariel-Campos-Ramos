package com.example.weather.Models

import java.text.SimpleDateFormat
import java.util.*

class Hour(val time: Long, val temp:Double, val precip:Double) {

    fun getFormattedTime():String{

        val formatter = SimpleDateFormat("h:mm a")

        val date = Date(time *100)

        val dayOfTheWeek = formatter.format(date)

        return dayOfTheWeek
    }





}


