package com.example.weather.UI

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.res.ResourcesCompat
import android.view.View
import com.android.volley.Request
import kotlinx.android.synthetic.main.activity_main.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Response
import com.example.weather.API.*
import com.example.weather.Extension.action
import com.example.weather.Extension.displaySnack
import com.example.weather.Models.CurrentWeather
import com.example.weather.Models.Day
import com.example.weather.Models.Hour
import org.json.JSONObject
import java.text.SimpleDateFormat
import android.annotation.SuppressLint
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName
    var days:ArrayList<Day> = ArrayList() // para que no se detenga la app
    var hours:ArrayList<Hour> = ArrayList()

    companion object{
        val DAYLY_WEATHER = "DAILY_WEATHER"
        val HOURlY_WEATHER = "HOURLY_WEATHER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.weather.R.layout.activity_main)

        tempTextView.text = getString(com.example.weather.R.string.temp_placeholder,0)

        precipTextView.text = this.getString(com.example.weather.R.string.precip_placeholder, 0)

        getWeather()
    }

    private fun getWeather() {

        val latitud = 12.434
        val longitud = -86.882
        val language = getString(com.example.weather.R.string.language)
        val units = getString(com.example.weather.R.string.units)

        val url = "$DARK_SKY_URL/$API_KEY/$latitud,$longitud?lang=$language&units=$units"

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> {
                //obtener nuestro clima actual con la clase JSONParse


                val responseJSON = JSONObject(it)

                val currentWeather = getCurrentWeatherFromJson(responseJSON)

                days = getDailyWeatherFromJson(responseJSON)

                hours = getHourlyWeatherFromJson(responseJSON)

                //asignar los valores a las view adecuadas

                buildCurrentWeatherUI(currentWeather)

            }, Response.ErrorListener {
                //para los errores que pueden ocurrir

                displayErrorMessage()


            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    private fun displayErrorMessage() { //crear una snackbar para mostar mensaje de error de red
        main.displaySnack(getString(com.example.weather.R.string.network_error),Snackbar.LENGTH_INDEFINITE){

            action(getString(com.example.weather.R.string.retry)){
                getWeather()
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun buildCurrentWeatherUI(currentWeather: CurrentWeather) {

        val sdf = SimpleDateFormat("dd-MM-yyyy \n\n hh:mm a")
        //val sdf = SimpleDateFormat("M")
        val date = Date(currentWeather.time * 1000)



        val precipProbability = (currentWeather.precip * 100).toInt()

        tempTextView.text = getString(com.example.weather.R.string.temp_placeholder,currentWeather.temp.toInt() )

        precipTextView.text = getString(com.example.weather.R.string.precip_placeholder, precipProbability)

        descriptionTextView.text = currentWeather.summary

        localizationTextView.text = sdf.format(date)

        iconimageView.setImageDrawable(ResourcesCompat.getDrawable(resources, currentWeather.getIconResource(),null))


    }

    fun startHourlyActivity(view: View){

        val intent = Intent(this,HourlyWeatherActivity::class.java).apply{

            putParcelableArrayListExtra(HOURlY_WEATHER, hours)
        }

        startActivity(intent)

    }

    fun startDailyActivity(view: View) {

        val intent = Intent(this, DailyWeatherActivity::class.java).apply {
            putParcelableArrayListExtra(DAYLY_WEATHER,days)
        }

        startActivity(intent)
    }
}
