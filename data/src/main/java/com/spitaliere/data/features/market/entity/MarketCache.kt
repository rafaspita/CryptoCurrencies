package com.spitaliere.data.features.market.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.spitaliere.data.platform.converter.MarketConverter
import com.spitaliere.domain.features.market.entity.Market
import com.spitaliere.domain.features.market.entity.MarketData

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
@Entity(tableName = "market")
data class MarketCache(
    @PrimaryKey
    var coinName: String,
    @TypeConverters(MarketConverter::class)
    var marketCaches: List<MarketCacheData>
)

fun MarketCache.mapToDomain() = Market(
    coinName = coinName,
    markets = marketCaches.mapToDomain()
)

data class MarketCacheData(
    val exchangeId : String = "",
    val baseId : String = "",
    val quoteId : String = "",
    val baseSymbol : String = "",
    val quoteSymbol : String = "",
    val volumeUsd24Hr : Double = 0.0,
    val priceUsd : Double = 0.0,
    val volumePercent : Double = 0.0
)

fun List<MarketCacheData>.mapToDomain() = map { it.mapToDomain() }

fun MarketCacheData.mapToDomain() = MarketData(
    exchangeId = exchangeId,
    baseId  = baseId,
    quoteId = quoteId,
    baseSymbol = baseSymbol,
    quoteSymbol = quoteSymbol,
    volumeUsd24Hr = volumeUsd24Hr,
    priceUsd = priceUsd,
    volumePercent = volumePercent
)