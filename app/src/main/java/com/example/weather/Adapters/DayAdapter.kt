package com.example.weather.Adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.weather.Models.Day
import com.example.weather.R
import kotlinx.android.synthetic.main.daily_item.view.*
import java.security.AccessControlContext

class DayAdapter(val context: Context, val datasource: ArrayList<Day>):BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


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