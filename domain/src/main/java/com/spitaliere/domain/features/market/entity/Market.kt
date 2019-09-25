package com.spitaliere.domain.features.market.entity

/**
 * Created by Rafael Spitaliere on 23/09/2019.
 **/
data class Market(
    val coinName: String = "",
    val markets: List<MarketData>
)