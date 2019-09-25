package com.spitaliere.data.features.currency.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.spitaliere.domain.features.currency.entity.Currency

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
@Entity(tableName = "currency")
data class CurrencyCache(
    @PrimaryKey
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
    var lastUpdate: Long = 0
)

fun CurrencyCache.mapToDomain(): Currency = Currency(
    id = id,
    rank = rank,
    symbol = symbol,
    name = name,
    supply = supply,
    maxSupply = maxSupply,
    marketCapUsd = marketCapUsd,
    volumeUsd24Hr = volumeUsd24Hr,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr,
    vwap24Hr = vwap24Hr,
    lastUpdate = lastUpdate
)

fun List<CurrencyCache>.mapToDomain() : List<Currency> = map { it.mapToDomain() }

fun Currency.mapToData(): CurrencyCache = CurrencyCache(
    id = id,
    rank = rank,
    symbol = symbol,
    name = name,
    supply = supply,
    maxSupply = maxSupply,
    marketCapUsd = marketCapUsd,
    volumeUsd24Hr = volumeUsd24Hr,
    priceUsd = priceUsd,
    changePercent24Hr = changePercent24Hr,
    vwap24Hr = vwap24Hr,
    lastUpdate = lastUpdate
)

fun List<Currency>.mapToData(): List<CurrencyCache> = map { it.mapToData() }