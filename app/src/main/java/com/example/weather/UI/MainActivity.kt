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
import com.example.weather.R
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName
    var days:ArrayList<Day> = ArrayList() // para que no se detenga la app

    companion object{
        val DAYLY_WEATHER = "DAILY_WEATHER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempTextView.text = getString(R.string.temp_placeholder,0)

        precipTextView.text = getString(R.string.precip_placeholder, 0)

        getWeather()
    }

    private fun getWeather() {
        val latitud = 37.8267
        val longitud = -122.4233
        val language = getString(R.string.language)
        val units = getString(R.string.units)

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
        main.displaySnack(getString(R.string.network_error),Snackbar.LENGTH_INDEFINITE){

            action(getString(R.string.retry)){
                getWeather()
            }
        }
    }

    private fun buildCurrentWeatherUI(currentWeather: CurrentWeather) {

        val precipProbability = (currentWeather.precip * 100).toInt()

        tempTextView.text = getString(R.string.temp_placeholder,currentWeather.temp.toInt() )

        precipTextView.text = getString(R.string.precip_placeholder, precipProbability)

        descriptionTextView.text = currentWeather.summary

        iconimageView.setImageDrawable(ResourcesCompat.getDrawable(resources, currentWeather.getIconResource(),null))


    }

    fun startHourlyActivity(view: View){

        val intent = Intent()
        intent.setClass(this,HourlyWeatherActivity::class.java)
        startActivity(intent)

    }

    fun startDailyActivity(view: View) {

        val intent = Intent(this, DailyWeatherActivity::class.java).apply {
            putParcelableArrayListExtra(DAYLY_WEATHER,days)
        }

        startActivity(intent)
    }
}
