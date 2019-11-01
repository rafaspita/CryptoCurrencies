package com.spitaliere.data.features.history.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.spitaliere.data.platform.converter.HistoryConverter
import com.spitaliere.domain.features.history.entity.History
import com.spitaliere.domain.features.history.entity.HistoryData
import com.spitaliere.domain.platform.extension.toDateHM

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
@Entity(tableName = "history")
data class HistoryCache(
    @PrimaryKey
    var coinName: String,
    @TypeConverters(HistoryConverter::class)
    var historyCache : List<HistoryCacheData>
)

fun HistoryCache.mapToDomain() : History = History(
    coinName = coinName,
    historyCache = historyCache.mapToDomain()
)

data class HistoryCacheData(
    var priceUsd : Float = 0f,
    var time: Long = 0
)

fun HistoryCacheData.mapToDomain() : HistoryData = HistoryData(
    priceUsd = priceUsd,
    time = time.toDateHM()
)

fun List<HistoryCacheData>.mapToDomain() : List<HistoryData> = map { it.mapToDomain() }