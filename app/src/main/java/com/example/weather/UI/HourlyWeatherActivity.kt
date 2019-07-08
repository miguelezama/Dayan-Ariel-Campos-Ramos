package com.example.weather.UI

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.weather.Adapters.HourAdapter
import com.example.weather.Models.Hour
import com.example.weather.R
import kotlinx.android.synthetic.main.activity_hourly_weather.*

class HourlyWeatherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hourly_weather)

        hourlyRecyclerView.layoutManager = LinearLayoutManager(this)

        intent.let{

            val hours:ArrayList<Hour> = it.getParcelableArrayListExtra(MainActivity.HOURlY_WEATHER)

            hourlyRecyclerView.adapter = HourAdapter(this, hours)


        }


    }
}
