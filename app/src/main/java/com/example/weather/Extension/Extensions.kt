package com.example.weather.Extension

import android.app.Notification
import android.support.design.widget.Snackbar
import android.view.OrientationEventListener
import android.view.View
import org.json.JSONArray
import org.json.JSONObject

operator fun JSONArray.iterator(): Iterator<JSONObject>
   =(0 until length()).asSequence().map { get(it) as JSONObject }.iterator()

fun View.displaySnack(message: String, length: Int = Snackbar.LENGTH_LONG, func: Snackbar.() -> Unit){

    val snackBar = Snackbar.make(this, message, length)

    snackBar.func()

    snackBar.show()
}

fun  Snackbar.action(message: String, listener: (View) -> Unit){

    setAction(message, listener)
}