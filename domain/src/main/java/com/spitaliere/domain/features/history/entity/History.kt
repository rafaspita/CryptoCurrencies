package com.spitaliere.domain.features.history.entity

/**
 * Created by Rafael Spitaliere on 24/09/2019.
 **/
data class History(
    var coinName: String,
    var historyCache : List<HistoryData>
)

data class HistoryData(
    var priceUsd : Float = 0f,
    var time: String = ""
)