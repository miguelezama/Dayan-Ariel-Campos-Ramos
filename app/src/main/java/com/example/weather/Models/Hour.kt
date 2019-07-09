package com.example.weather.Models

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*

data class Hour(val time: Long, val temp:Double, val precipProbability:Double, val timeZone: String): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()
    )



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(time)
        parcel.writeDouble(temp)
        parcel.writeDouble(precipProbability)
        parcel.writeString(timeZone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hour> {
        override fun createFromParcel(parcel: Parcel): Hour {
            return Hour(parcel)
        }

        override fun newArray(size: Int): Array<Hour?> {
            return arrayOfNulls(size)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getFormattedTime():String{

        val formatter = SimpleDateFormat("h:mm a")

        formatter.timeZone = TimeZone.getTimeZone(timeZone)

        val date = Date(time *100)

        val dayOfTheWeek = formatter.format(date)

        return dayOfTheWeek
    }
}


