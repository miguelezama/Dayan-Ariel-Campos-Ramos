package com.example.weather.UI

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.weather.Adapters.DayAdapter
import com.example.weather.Models.Day
import com.example.weather.R
import kotlinx.android.synthetic.main.activity_daily_weather.*

class DailyWeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_weather)

        intent.let {

            //rellenar nuestra lista

            val days:ArrayList<Day> = it.getParcelableArrayListExtra(MainActivity.DAYLY_WEATHER)

            val baseAdapter = DayAdapter(this,days)

            dailyListView.adapter = baseAdapter // para mostrar el clima por dia
        }


    }
}
