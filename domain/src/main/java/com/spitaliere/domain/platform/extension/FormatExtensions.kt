package com.spitaliere.domain.platform.extension

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
fun Double.format(fracDigits: Int? = null): String {
    val df = DecimalFormat()
    df.maximumFractionDigits  = if (this@format < 100 && fracDigits == null) 4 else fracDigits?: 2
    return df.format(this)
}

fun Double.formatWithPercent(fracDigits: Int):String = this.format(fracDigits) + "%"


fun Long.toDateHM(): String {
    val date = Date(this)
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(date)
}

fun Long.toDateComplete(): String{
    val date = Date(this)
    val format = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())
    return format.format(date)
}