package com.example.realworldconduitkotlin.extensions

import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*


//to format the ISO timestamp
fun TextView.formatDate(timeStamp: String) {
    val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val reqDateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())

    val date = isoDateFormat.parse(timeStamp)
    //putting the required date format in text of TextView
    text = reqDateFormat.format(date)
}