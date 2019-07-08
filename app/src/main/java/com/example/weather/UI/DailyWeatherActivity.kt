package com.example.weather.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.weather.Models.Day
import com.example.weather.R

class DailyWeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_weather)

        intent.let {

            //rellenar nuestra lista

            val days:ArrayList<Day> = it.getParcelableArrayListExtra(MainActivity.DAYLY_WEATHER)

            Toast.makeText(this, days.get(0).getFormattedTime(), Toast.LENGTH_LONG).show()
        }


    }
}
