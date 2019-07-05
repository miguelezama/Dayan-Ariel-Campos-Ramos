package com.example.weather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.res.ResourcesCompat
import android.util.Log
import com.android.volley.Request
import com.example.weather.API.API_KEY
import com.example.weather.API.DARK_SKY_URL
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.example.weather.API.JSONParser
import com.example.weather.Models.CurrentWeather
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.simpleName
    val jsonParser = JSONParser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            Response.Listener<String> { response ->
                //obtener nuestro clima actual con la clase JSONParse


                val responseJSON = JSONObject(response)

                val currentWeather = jsonParser.getCurrentWeatherFromJson(responseJSON)

                //asignar los valores a las view adecuadas

                buildCurrentWeatherUI(currentWeather)

            }, Response.ErrorListener {
                //para los errores que pueden ocurrir

                val snackbar = Snackbar.make(main,"NETWORK ERROR", Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY", {
                        getWeather()
                    })

                snackbar.show()
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    private fun buildCurrentWeatherUI(currentWeather: CurrentWeather) {

        val precipProbability = (currentWeather.precip * 100).toInt()

        tempTextView.text = getString(R.string.temp_placeholder,currentWeather.temp.toInt() )

        precipTextView.text = getString(R.string.precip_placeholder, precipProbability)

        descriptionTextView.text = currentWeather.summary

        iconimageView.setImageDrawable(ResourcesCompat.getDrawable(resources, currentWeather.getIconResource(),null))


    }
}
