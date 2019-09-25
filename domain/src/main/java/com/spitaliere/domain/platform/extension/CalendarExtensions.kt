package com.spitaliere.domain.platform.extension

import java.util.*

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
fun Calendar.passedAMinute(long: Long) : Boolean{
    val diff = timeInMillis - long
    return diff > 60000
}

fun Calendar.getTodayZeroHour() : Long = this.apply {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
}.timeInMillis