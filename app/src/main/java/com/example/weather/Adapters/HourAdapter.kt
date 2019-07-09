package com.example.weather.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weather.Models.Hour
import com.example.weather.R
import kotlinx.android.synthetic.main.hourly_item.view.*

class HourAdapter(val context: Context, val days: ArrayList<Hour>): RecyclerView.Adapter<HourAdapter.HourViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) 
        = holder.bind(days[position])


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder
          = HourViewHolder(layoutInflater.inflate(R.layout.hourly_item, parent, false))



    override fun getItemCount(): Int = days.size




    class HourViewHolder(hourlyItemView: View): RecyclerView.ViewHolder(hourlyItemView){

        @SuppressLint("SetTextI18n")
        fun bind(hour: Hour) = with(itemView){

            hourTextView.text = hour.getFormattedTime()

            hourPropTextView.text = "${(hour.precipProbability* 100).toInt().toString()} %"

            hourTempTextView.text = "${hour.temp.toInt().toString()} C"
        }
    }
}