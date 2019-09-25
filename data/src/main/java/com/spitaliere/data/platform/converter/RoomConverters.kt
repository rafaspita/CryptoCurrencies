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
open class ConverterBase<ENTITY> {

    private val type: Type = object : TypeToken<List<ENTITY>>() {}.type

    @TypeConverter
    fun fromList(list: List<ENTITY>) : String = Gson().toJson(list, type)

    @TypeConverter
    fun toList(items: String): List<ENTITY> = Gson().fromJson(items, type)
}

class HistoryConverter : ConverterBase<HistoryCacheData>()

class MarketConverter : ConverterBase<MarketCacheData>()