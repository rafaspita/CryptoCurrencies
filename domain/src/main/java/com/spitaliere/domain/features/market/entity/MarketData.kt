package com.spitaliere.domain.features.market.entity

import com.spitaliere.domain.platform.extension.format
import com.spitaliere.domain.platform.extension.formatWithPercent

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
data class MarketData(
    val exchangeId : String = "",
    val baseId : String = "",
    val quoteId : String = "",
    val baseSymbol : String = "",
    val quoteSymbol : String = "",
    val volumeUsd24Hr : Double = 0.0,
    val priceUsd : Double = 0.0,
    val volumePercent : Double = 0.0
){
    fun getPrice(): String = priceUsd.format(null)
    fun getVolumeUsd():String = volumeUsd24Hr.format(null)
    fun getVolume():String = volumePercent.formatWithPercent(2)
}