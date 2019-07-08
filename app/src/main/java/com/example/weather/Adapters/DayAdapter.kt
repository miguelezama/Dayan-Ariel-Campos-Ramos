package com.example.weather.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.weather.Models.Day
import com.example.weather.R

class DayAdapter(val context: Context, val datasource: ArrayList<Day>):BaseAdapter() {
    private val layoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parentView: ViewGroup?): View {


        val view:View
        val viewHolder: ViewHolder

        if (convertView == null){
            view = layoutInflater.inflate(R.layout.daily_item, parentView, false)

            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            viewHolder = convertView.tag as ViewHolder
            view = convertView
        }

        val currentDay = datasource[position]

        viewHolder.apply {

            dayTextView.text = currentDay.getFormattedTime()
            minTextView.text = "Min ${currentDay.minTemp.toInt()} C"
            maxTextView.text = "Max ${currentDay.maxTemp.toInt()} C"
        }


        return view
    }

    override fun getItem(position: Int): Any {

        return datasource[position]
    }

    override fun getItemId(position: Int): Long {

        return 0
    }

    override fun getCount(): Int {


        return datasource.size
    }

    private class ViewHolder(view: View){

        val dayTextView: TextView = view.findViewById(R.id.dayTextView)
        val minTextView: TextView = view.findViewById(R.id.minTextView)
        val maxTextView: TextView = view.findViewById(R.id.maxTextView)


    }
}