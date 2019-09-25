package com.spitaliere.domain.features.currency.entity

import com.spitaliere.domain.platform.extension.format
import com.spitaliere.domain.platform.extension.formatWithPercent
import com.spitaliere.domain.platform.extension.toDateComplete
import java.io.Serializable

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
data class Currency(
    var id: String = "",
    var rank: Int = 0,
    var symbol: String = "",
    var name: String = "",
    var supply: Double = 0.0,
    var maxSupply: Double = 0.0,
    var marketCapUsd: Double = 0.0,
    var volumeUsd24Hr: Double = 0.0,
    var priceUsd: Double = 0.0,
    var changePercent24Hr: Double = 0.0,
    var vwap24Hr: Double = 0.0,
    var active: Boolean = true,
    var lastUpdate: Long = 0
) : Serializable {
    fun getPrice():String = priceUsd.format()
    fun getChange24h():String = changePercent24Hr.formatWithPercent(2)
    fun getCoinSupply():String = "$" + supply.format()
    fun getCoinMaxSupply():String = "$" + maxSupply.format()
    fun getCoinMarketCapUsd():String = "$" + marketCapUsd.format()
    fun getCoinVolumeUsd24Hr():String = "$" + volumeUsd24Hr.format()
    fun getLastUpdateFormat(): String = lastUpdate.toDateComplete()
}