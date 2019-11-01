package com.spitaliere.data.platform.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spitaliere.data.features.history.entity.HistoryCacheData
import com.spitaliere.data.features.market.entity.MarketCacheData
import java.lang.reflect.Type

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
class HistoryConverter{
    val type: Type = object : TypeToken<List<HistoryCacheData>>() {}.type

    @TypeConverter
    fun fromList(list: List<HistoryCacheData>) : String = Gson().toJson(list, type)

    @TypeConverter
    fun toList(items: String): List<HistoryCacheData> = Gson().fromJson(items, type)
}

class MarketConverter {
    val type: Type = object : TypeToken<List<MarketCacheData>>() {}.type

    @TypeConverter
    fun fromList(list: List<MarketCacheData>) : String = Gson().toJson(list, type)

    @TypeConverter
    fun toList(items: String): List<MarketCacheData> = Gson().fromJson(items, type)
}